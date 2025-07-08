package com.example.sangue.domain.address.dto;

public record AddressDTO(
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado
) {
}
