package com.example.sangue.domain.donation.controller.openApi;

import com.example.sangue.domain.donation.dto.DonationCreateDTO;
import com.example.sangue.domain.donation.dto.DonationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "Doações", description = "Endpoints para gerenciamento de doações")
public interface DonationControllerOpenApi {

    @Operation(summary = "Busca todas as doações", description = "Retorna uma lista de doações realizada pelo usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de doações encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum doação encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    ResponseEntity<Page<DonationResponseDTO>> listar(Pageable pageable, Long campanhaId, Long doadorId);

    @Operation(summary = "Cria uma nova doação", description = "Cria um novo doação e o vincula ao usuário. Requer permissão de ADM.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Apenas usuários ADM podem criar.")
    })
    ResponseEntity<DonationResponseDTO> registrar(DonationCreateDTO dto);

    @Operation(summary = "Busca todos os doadores", description = "Retorna uma lista de doares. Requer permissão de ADM.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de doadores encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum doador encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    ResponseEntity<DonationResponseDTO> buscarPorId(Long id);
}
