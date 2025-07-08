package com.example.sangue.domain.campaign.controller.openApi;

import com.example.sangue.domain.campaign.dto.CampaignDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Campanhas", description = "Endpoints para gerenciamento de campanhas")
public interface CampaignControllerOpenApi {

    @Operation(summary = "Busca todas as campanha", description = "Retorna uma campanha uma lista de campanhas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de campanhas encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma campanha encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    ResponseEntity<Page<CampaignDTO>> findAll(Pageable pageable);

    @Operation(summary = "Busca uma campanha por ID", description = "Retorna uma campanha específica com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campanha encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Campanha não encontrada"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    ResponseEntity<CampaignDTO> findById(@PathVariable Long id);

    @Operation(summary = "Cria uma nova campanha", description = "Cria uma nova campanha.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Campanha criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    ResponseEntity<CampaignDTO> create(CampaignDTO dto);

    @Operation(summary = "Atualiza uma campanha específica", description = "Atualiza dados de uma campanha específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Campanha atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    ResponseEntity<CampaignDTO> update(Long id, CampaignDTO dto);
}
