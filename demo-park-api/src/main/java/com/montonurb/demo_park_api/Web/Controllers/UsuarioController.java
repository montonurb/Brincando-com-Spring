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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@Valid @RequestBody UsuarioCreateDto usuarioDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(usuarioDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> alterarSenha(@PathVariable Long id, @Valid @RequestBody UsuarioPasswordDto usuarioDto) {
        usuarioService.editarSenha(id, usuarioDto.getOldPassword(), usuarioDto.getNewPassword(), usuarioDto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
}
