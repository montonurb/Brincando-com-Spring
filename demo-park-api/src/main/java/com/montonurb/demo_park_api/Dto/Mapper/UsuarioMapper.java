package com.montonurb.demo_park_api.Dto.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.montonurb.demo_park_api.Dto.UsuarioCreateDto;
import com.montonurb.demo_park_api.Dto.UsuarioResponseDto;
import com.montonurb.demo_park_api.Entities.Usuario;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto usuarioDto) {
        return new ModelMapper().map(usuarioDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario,UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios) {
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
