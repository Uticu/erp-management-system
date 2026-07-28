package com.StrugarMaximIonut.erp.controller;

import com.StrugarMaximIonut.erp.dto.client.ClientDTO;
import com.StrugarMaximIonut.erp.dto.client.ClientRequestDTO;
import com.StrugarMaximIonut.erp.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getClients(){
        List<ClientDTO> list = clientService.getAllClients();
        return  ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer id){
        ClientDTO clientDTO = clientService.getClientById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> modifyClient(@RequestBody ClientRequestDTO clientRequestDTO, @PathVariable Integer id){
        ClientDTO clientDTO = clientService.modifyClient(clientRequestDTO, id);
        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Integer id){
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> insertClient(@RequestBody ClientRequestDTO clientRequestDTO){
        ClientDTO clientDTO = clientService.insertClient(clientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDTO);
    }


}
