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

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> list = clientService.getAllClients();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer id) {
        ClientDTO clientDTO = clientService.getClientById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping(value = "/search", params = "email")
    public ResponseEntity<ClientDTO> getClientByEmail(@RequestParam String email){
        ClientDTO clientDTO = clientService.getClientByEmail(email);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<List<ClientDTO>> getClientByName(@RequestParam String name){
        List<ClientDTO> list = clientService.getClientsByName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameStartWith")
    public ResponseEntity<List<ClientDTO>> getClientsStartWith(@RequestParam String nameStartWith){
        List<ClientDTO> list = clientService.getClientStartWithString(nameStartWith);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameEndsWith")
    public ResponseEntity<List<ClientDTO>> getClientsEndingWith(@RequestParam String nameEndsWith){
        List<ClientDTO> list = clientService.getClientEndingWithString(nameEndsWith);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameContains")
    public ResponseEntity<List<ClientDTO>> getClientsContains(@RequestParam String nameContains){
        List<ClientDTO> list = clientService.getClientContainsString(nameContains);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "clientPhoneNumber")
    public ResponseEntity<ClientDTO> getClientByPhoneNumber(@RequestParam String clientPhoneNumber){
        ClientDTO clientDTO = clientService.getClientByPhoneNumber(clientPhoneNumber);
        return ResponseEntity.ok(clientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> modifyClient(@RequestBody ClientRequestDTO clientRequestDTO, @PathVariable Integer id) {
        ClientDTO clientDTO = clientService.modifyClient(clientRequestDTO, id);
        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Integer id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> insertClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientDTO clientDTO = clientService.insertClient(clientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDTO);
    }


}
