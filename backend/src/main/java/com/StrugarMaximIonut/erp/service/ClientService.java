package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.dto.client.ClientDTO;
import com.StrugarMaximIonut.erp.dto.client.ClientDTOMapper;
import com.StrugarMaximIonut.erp.dto.client.ClientRequestDTO;
import com.StrugarMaximIonut.erp.dto.client.ClientRequestMapper;
import com.StrugarMaximIonut.erp.exception.client.ClientFoundException;
import com.StrugarMaximIonut.erp.exception.client.ClientNotFoundException;
import com.StrugarMaximIonut.erp.exception.client.NoClientsException;
import com.StrugarMaximIonut.erp.model.Client;
import com.StrugarMaximIonut.erp.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientDTOMapper clientDTOMapper;
    private final ClientRequestMapper clientRequestMapper;

    public ClientService(ClientRepository clientRepository, ClientDTOMapper clientDTOMapper, ClientRequestMapper clientRequestMapper) {
        this.clientRepository = clientRepository;
        this.clientDTOMapper = clientDTOMapper;
        this.clientRequestMapper = clientRequestMapper;

    }

    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new NoClientsException("The database has no clients in it!");
        }

        return clients.stream()
                .map(clientDTOMapper)
                .collect(Collectors.toList());
    }

    private Client findClientEntityById(Integer id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " is not in the database"));
    }

    private Client findClientEntityByEmail(String email){
        Client client = clientRepository.findByClientEmail(email);
        if(client == null){
            throw new ClientNotFoundException("Client with email " + email + " is not in the database");
        }
        return client;
    }

    public ClientDTO getClientByEmail(String email){
        Client client = this.findClientEntityByEmail(email);
        return clientDTOMapper.apply(client);
    }

    public List<ClientDTO> getClientsByName(String name){
        List<Client> clients = clientRepository.findByClientName(name);

        if(clients.isEmpty()){
            throw new NoClientsException("There are no clients with this name in the database");
        }

        return clients.stream()
                .map(clientDTOMapper)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(Integer id){
        Client client = findClientEntityById(id);
        return clientDTOMapper.apply(client);
    }

    public List<ClientDTO> getClientStartWithString(String nameStartWith){
        List<Client> clients = clientRepository.findClientsByClientNameStartsWith(nameStartWith);
        if(clients.isEmpty()){
            throw new NoClientsException("No clients starting with " + nameStartWith + " are in the database");
        }
        return clients.stream()
                .map(clientDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ClientDTO> getClientEndingWithString(String nameEndsWith){
        List<Client> clients = clientRepository.findClientsByClientNameEndsWith(nameEndsWith);
        if(clients.isEmpty()){
            throw new NoClientsException("No clients ending with " + nameEndsWith + " are in the database");
        }
        return clients.stream()
                .map(clientDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ClientDTO> getClientContainsString(String nameContains){
        List<Client> clients = clientRepository.findByClientNameContains(nameContains);
        if(clients.isEmpty()){
            throw new NoClientsException("No clients that contains " + nameContains + " are in the database");
        }

        return clients.stream()
                .map(clientDTOMapper)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientByPhoneNumber(String clientPhoneNumber){
        Client client = clientRepository.findByClientPhoneNumber(clientPhoneNumber);
        if(client == null){
            throw new ClientNotFoundException("Client with phone number " + clientPhoneNumber + " is not in the database");
        }
        return clientDTOMapper.apply(client);
    }

    public void deleteClientById(Integer id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client with id " + id + " is not in the database");
        }
        clientRepository.deleteById(id);
    }

    public ClientDTO insertClient(ClientRequestDTO clientRequestDTO) {
        if (clientRepository.existsByClientEmail(clientRequestDTO.clientEmail())){
            throw new ClientFoundException("Client with email " + clientRequestDTO.clientEmail() + " is already in the database");
        }
        if(clientRepository.existsByClientPhoneNumber(clientRequestDTO.clientPhoneNumber())){
            throw new ClientFoundException(("Client with phone number " + clientRequestDTO.clientPhoneNumber() + " is already in the database"));
        }
        Client client = clientRequestMapper.apply(clientRequestDTO);
        clientRepository.save(client);

        return clientDTOMapper.apply(client);
    }

    public ClientDTO modifyClient(ClientRequestDTO clientRequestDTO, Integer id){
        Client client = this.findClientEntityById(id);

        if(!client.getClientEmail().equals(clientRequestDTO.clientEmail()) &&
        clientRepository.existsByClientEmail(clientRequestDTO.clientEmail())){
            throw new ClientFoundException("This email is already used by another client");
        }

        if(!client.getClientPhoneNumber().equals(clientRequestDTO.clientPhoneNumber()) &&
        clientRepository.existsByClientPhoneNumber(clientRequestDTO.clientPhoneNumber())){
            throw new ClientFoundException("This phone number is already used by another client");
        }

        client.setClientName(clientRequestDTO.clientName());
        client.setClientEmail(clientRequestDTO.clientEmail());
        client.setClientPhoneNumber(clientRequestDTO.clientPhoneNumber());
        client.setClientAddress(clientRequestDTO.clientAddress());

        clientRepository.save(client);

        return clientDTOMapper.apply(client);
    }
}