package com.example.sangue.domain.user.controller.openApi;

import com.example.sangue.domain.user.dto.UserCreateDTO;
import com.example.sangue.domain.user.dto.UserResponseDTO;
import com.example.sangue.domain.user.dto.UserUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public interface UserControllerOpenApi {

    @Operation(summary = "Busca todos os usuário", description = "Retorna um usuário uma lista de usuários.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    ResponseEntity<Page<UserResponseDTO>> findAll(Pageable pageable);

    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário e o torna CLIENT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
    })
    ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreateDTO dto);


    @Operation(summary = "Atualiza um usuário específico", description = "Atualiza dados de um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado. As atualizações só podem ocorrer do próprio usuário logado.")
    })
    ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto);

    @Operation(summary = "Desativa um usuário específico", description = "Desativa um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "usuário desativado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Os usuários só podem desativar o próprio usuário.")
    })
    ResponseEntity<Void> delete(Long id);
}
