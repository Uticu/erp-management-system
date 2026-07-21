package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.dto.ClientDTO;
import com.StrugarMaximIonut.erp.dto.ClientDTOMapper;
import com.StrugarMaximIonut.erp.model.Client;
import com.StrugarMaximIonut.erp.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientDTOMapper clientDTOMapper;

    public ClientService(ClientRepository clientRepository, ClientDTOMapper clientDTOMapper){
        this.clientRepository = clientRepository;
        this.clientDTOMapper = clientDTOMapper;
    }

    public List<ClientDTO> getAllClients(){
        return clientRepository.findAll()
                .stream()
                .map(clientDTOMapper)
                .collect(Collectors.toList());
    }
}
