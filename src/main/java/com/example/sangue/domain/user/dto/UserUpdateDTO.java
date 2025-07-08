package com.example.sangue.domain.user.dto;

import com.example.sangue.domain.address.dto.AddressDTO;
import jakarta.validation.constraints.Email;

public record UserUpdateDTO(
        String nome,
        @Email String email,
        String telefone,
        AddressDTO endereco
) {
}
