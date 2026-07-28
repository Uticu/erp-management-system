package com.StrugarMaximIonut.erp.dto.client;

import com.StrugarMaximIonut.erp.model.Client;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientDTOMapper implements Function<Client, ClientDTO> {
    @Override
    public ClientDTO apply(Client client){
        return new ClientDTO(
                client.getClientID(),
                client.getClientName(),
                client.getClientEmail(),
                client.getClientAddress(),
                client.getClientPhoneNumber())  ;
    }

}
