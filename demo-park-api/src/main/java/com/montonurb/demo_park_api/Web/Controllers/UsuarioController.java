package com.montonurb.demo_park_api.Web.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montonurb.demo_park_api.Entities.Usuario;
import com.montonurb.demo_park_api.Services.UsuarioService;
import com.montonurb.demo_park_api.Web.Dto.UsuarioCreateDto;
import com.montonurb.demo_park_api.Web.Dto.UsuarioPasswordDto;
import com.montonurb.demo_park_api.Web.Dto.UsuarioResponseDto;
import com.montonurb.demo_park_api.Web.Dto.Mapper.UsuarioMapper;
import com.montonurb.demo_park_api.Web.Exceptions.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Operation(summary = "Criar usuário.", description = "Criando um usuário.", responses = {
        @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso!",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
        @ApiResponse(responseCode = "409", description = "E-mail do usuário já foi cadastrado!",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
        @ApiResponse(responseCode = "422", description = "Dados informados inválidos!",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@Valid @RequestBody UsuarioCreateDto usuarioDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(usuarioDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @Operation(summary = "Pesquisar usuário por ID.", description = "Pesquisando um usuário informando apenas o ID dele.", responses = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
        @ApiResponse(responseCode = "484", description = "Usuário não encontrado!",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @Operation(summary = "Alterar senha do usuário.", description = "Alterando a senha do usuário.", responses = {
        @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso!",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode = "404", description = "Não foi possível encontrar o usuário para alterar a senha!",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
        @ApiResponse(responseCode = "400", description = "A senha informada não é válida!",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> alterarSenha(@PathVariable Long id, @Valid @RequestBody UsuarioPasswordDto usuarioDto) {
        usuarioService.editarSenha(id, usuarioDto.getOldPassword(), usuarioDto.getNewPassword(), usuarioDto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar todos os usuários.", description = "Buscando todos os usuários cadastrados.", responses = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrados!",
            content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class)))),
        @ApiResponse(responseCode = "400", description = "Não foi possível encontrar usuários!",
            content = @Content(mediaType = "Application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/")
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
}
