package com.montonurb.demo_park_api.Services;

import org.springframework.stereotype.Service;

import com.montonurb.demo_park_api.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
}
