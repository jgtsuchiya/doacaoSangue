package com.example.sangue.domain.user.dto;

import com.example.sangue.domain.address.dto.AddressDTO;
import com.example.sangue.domain.user.enums.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserCreateDTO(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String senha,
        String telefone,
        @NotBlank String tipoSanguineo,
        LocalDate dataNascimento,
        @NotNull UserRole userRole,
        @Valid AddressDTO endereco
) {
}
