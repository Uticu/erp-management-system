package com.StrugarMaximIonut.erp.dto.client;

import com.StrugarMaximIonut.erp.model.Client;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientRequestMapper implements Function<ClientRequestDTO, Client> {
    @Override
    public Client apply(ClientRequestDTO clientRequestDTO){
        Client client = new Client();
        client.setClientName(clientRequestDTO.clientName());
        client.setClientAddress(clientRequestDTO.clientAddress());
        client.setClientEmail(clientRequestDTO.clientEmail());
        client.setClientPhoneNumber(clientRequestDTO.clientPhoneNumber());

        return client;
    }
}
