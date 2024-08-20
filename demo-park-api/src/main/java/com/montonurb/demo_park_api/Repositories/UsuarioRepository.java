package com.montonurb.demo_park_api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.montonurb.demo_park_api.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
