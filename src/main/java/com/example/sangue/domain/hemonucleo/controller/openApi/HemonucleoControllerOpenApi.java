package com.example.sangue.domain.hemonucleo.controller.openApi;

import com.example.sangue.domain.hemonucleo.dto.HemonucleoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Hemonúcleos", description = "Endpoints para gerenciamento de hemonúcleos")
public interface HemonucleoControllerOpenApi {

    @Operation(summary = "Busca todos os hemonúcleo", description = "Retorna um hemonúcleo uma lista de hemonúcleos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de hemonúcleos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum hemonúcleo encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    ResponseEntity<Page<HemonucleoDTO>> findAll(Pageable pageable);

    @Operation(summary = "Busca um hemonúcleo por ID", description = "Retorna um hemonúcleo específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hemonúcleo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hemonúcleo não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    ResponseEntity<HemonucleoDTO> findById(Long id);

    @Operation(summary = "Cria um novo hemonúcleo", description = "Cria um novo hemonúcleo e o vincula ao usuário ADM autenticado. Requer permissão de ADM.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hemonúcleo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Apenas usuários ADM podem criar.")
    })
    ResponseEntity<HemonucleoDTO> createHemonucleo(Long idUsuarioAdm, HemonucleoDTO dto);


    @Operation(summary = "Atualiza um hemonúcleo específico", description = "Atualiza dados de um hemonúcleo específico. Requer permissão de ADM.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hemonúcleo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Apenas usuários ADM podem atualizar.")
    })
    ResponseEntity<HemonucleoDTO> update(Long id, HemonucleoDTO dto);

    @Operation(summary = "Desativa um hemonúcleo específico", description = "Desativa um hemonúcleo específico. Requer permissão de ADM.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hemonúcleo desativado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Apenas usuários ADM podem atualizar.")
    })
    ResponseEntity<Void> delete(Long id);
}