package com.example.sangue.domain.donation.dto;

import java.time.LocalDateTime;

public record DonationResponseDTO(
        Long id,
        LocalDateTime dataDoacao,
        Long idDoador,
        String nomeDoador,
        String cpfDoador,
        Long idHemonucleo,
        String nomeHemonucleo
) {
}
