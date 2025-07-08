package com.example.sangue.domain.campaign.dto;

import java.time.LocalDate;

public record CampaignDTO(
        Long id,
        String nome,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataTermino,
        Integer metaDoacoes,
        String tipoSanguineoAlvo,
        Long idHemonucleo,
        String nomeHemonucleo
) {
}
