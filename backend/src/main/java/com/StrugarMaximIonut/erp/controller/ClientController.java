package com.StrugarMaximIonut.erp.controller;

import com.StrugarMaximIonut.erp.dto.ClientDTO;
import com.StrugarMaximIonut.erp.model.Client;
import com.StrugarMaximIonut.erp.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping()
    public List<ClientDTO> getClients(){
        return clientService.getAllClients();
    }

}
