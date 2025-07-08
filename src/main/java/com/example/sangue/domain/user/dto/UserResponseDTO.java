package com.example.sangue.domain.user.dto;

import com.example.sangue.domain.address.dto.AddressDTO;
import com.example.sangue.domain.user.enums.UserRole;

import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        String tipoSanguineo,
        LocalDate dataNascimento,
        Boolean ativo,
        AddressDTO endereco,
        UserRole userRole
) {
}
