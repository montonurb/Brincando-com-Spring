package com.montonurb.demo_park_api.Services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montonurb.demo_park_api.Entities.Usuario;
import com.montonurb.demo_park_api.Exceptions.EntityNotFoundException;
import com.montonurb.demo_park_api.Exceptions.PasswordViolationException;
import com.montonurb.demo_park_api.Exceptions.UsernameUniqueViolationException;
import com.montonurb.demo_park_api.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado!", usuario.getUsername()));
        }
    }

    @Transactional(readOnly=true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(String.format("Usuário id={%s} não encontrado!", id))
        );
        
    }

    @Transactional
    public Usuario editarSenha(Long id, String oldPassword, String newPassword, String confirmPassword) {
        if(!newPassword.equals(confirmPassword)) {
            throw new PasswordViolationException("A nova senha não cofere com a confirmação de senha!");
        }

        Usuario user = buscarPorId(id);

        if(!user.getPassword().equals(oldPassword)) {
            throw new PasswordViolationException("Senha informada não confere com a atual!");
        }
        
        user.setPassword(newPassword);
        return user;
    }

    @Transactional(readOnly=true)
    public List<Usuario> buscarTodos() {
        List<Usuario> users = usuarioRepository.findAll();
        return users;
    }
}
