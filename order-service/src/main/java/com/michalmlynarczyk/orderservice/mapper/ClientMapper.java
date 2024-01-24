package com.michalmlynarczyk.orderservice.mapper;

import com.michalmlynarczyk.orderservice.model.dto.ClientDto;
import com.michalmlynarczyk.orderservice.model.entity.Client;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {

    public static Client toEntity(final ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        return new Client(
                clientDto.firstName(),
                clientDto.lastName(),
                clientDto.email()
        );
    }


    public static ClientDto toDto(final Client client) {
        if (client == null) {
            return null;
        }
        return new ClientDto(
                client.getFirstName(),
                client.getLastName(),
                client.getEmail()
        );
    }
}
