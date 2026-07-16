package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.model.Client;
import com.StrugarMaximIonut.erp.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }


}
