package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.dto.ClientDTO;
import com.StrugarMaximIonut.erp.dto.ClientDTOMapper;
import com.StrugarMaximIonut.erp.dto.ClientRequestDTO;
import com.StrugarMaximIonut.erp.dto.ClientRequestMapper;
import com.StrugarMaximIonut.erp.exception.ClientFoundException;
import com.StrugarMaximIonut.erp.exception.ClientNotFoundException;
import com.StrugarMaximIonut.erp.exception.NoClientsException;
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

    public ClientDTO getClientById(Integer id){
        Client client = findClientEntityById(id);
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
        client.setClientName(clientRequestDTO.clientName());
        client.setClientEmail(clientRequestDTO.clientEmail());
        client.setClientPhoneNumber(clientRequestDTO.clientPhoneNumber());
        client.setClientAddress(clientRequestDTO.clientAddress());

        clientRepository.save(client);

        return clientDTOMapper.apply(client);
    }
}