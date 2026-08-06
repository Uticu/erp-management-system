package com.StrugarMaximIonut.erp.controller;

import com.StrugarMaximIonut.erp.dto.client.ClientDTO;
import com.StrugarMaximIonut.erp.dto.client.ClientRequestDTO;
import com.StrugarMaximIonut.erp.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@Validated
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
    public ResponseEntity<ClientDTO> getClientById(@Min(value = 1, message = "Id must be atleast 1")
                                                   @PathVariable Integer id) {
        ClientDTO clientDTO = clientService.getClientById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping(value = "/search", params = "email")
    public ResponseEntity<ClientDTO> getClientByEmail
            (@Email(message = "Email must be valid")
             @NotBlank(message = "Email is mandatory")
             @Size(max = 255, message = "Email cannot exceed 255 characters")
             @RequestParam String email) {
        ClientDTO clientDTO = clientService.getClientByEmail(email);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<List<ClientDTO>> getClientByName
            (@NotBlank(message = "Name is mandatory")
             @Size(max = 255, message = "Name cannot exceed 255 characters")
             @RequestParam String name) {
        List<ClientDTO> list = clientService.getClientsByName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameStartWith")
    public ResponseEntity<List<ClientDTO>> getClientsStartWith(
            @NotBlank(message = "Cannot search with null string")
            @RequestParam String nameStartWith) {
        List<ClientDTO> list = clientService.getClientStartWithString(nameStartWith);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameEndsWith")
    public ResponseEntity<List<ClientDTO>> getClientsEndingWith(
            @NotBlank(message = "Cannot search with null string")
            @RequestParam String nameEndsWith) {
        List<ClientDTO> list = clientService.getClientEndingWithString(nameEndsWith);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameContains")
    public ResponseEntity<List<ClientDTO>> getClientsContains(
            @NotBlank(message = "Cannot search with null string")
            @RequestParam String nameContains) {
        List<ClientDTO> list = clientService.getClientContainsString(nameContains);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "clientPhoneNumber")
    public ResponseEntity<ClientDTO> getClientByPhoneNumber(
            @Size(min = 1, max = 20, message = "Phone number must contain between 1 and 20 characters")
            @NotBlank(message = "Phone number is mandatory")
            @RequestParam String clientPhoneNumber) {
        ClientDTO clientDTO = clientService.getClientByPhoneNumber(clientPhoneNumber);
        return ResponseEntity.ok(clientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> modifyClient(
            @Valid @RequestBody ClientRequestDTO clientRequestDTO,
            @Min(value = 1, message = "Id must be atleast 1") @PathVariable Integer id) {
        ClientDTO clientDTO = clientService.modifyClient(clientRequestDTO, id);
        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(
            @Min(value = 1, message = "Id must be atleast 1")
            @PathVariable Integer id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> insertClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientDTO clientDTO = clientService.insertClient(clientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDTO);
    }


}
