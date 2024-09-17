package com.montonurb.demo_park_api.Web.Dto.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.montonurb.demo_park_api.Entities.Usuario;
import com.montonurb.demo_park_api.Web.Dto.UsuarioCreateDto;
import com.montonurb.demo_park_api.Web.Dto.UsuarioResponseDto;

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
