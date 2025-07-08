package com.example.sangue.domain.hemonucleo.dto;

import com.example.sangue.domain.address.dto.AddressDTO;

public record HemonucleoDTO(
        Long id,
        String nome,
        String cnpj,
        String email,
        String telefone,
        AddressDTO endereco
) {
}
