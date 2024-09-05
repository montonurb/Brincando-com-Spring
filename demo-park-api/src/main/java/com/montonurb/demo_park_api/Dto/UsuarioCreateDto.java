package com.montonurb.demo_park_api.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioCreateDto {
    @Email(message="Formato do e-mail inválido!")
    @NotBlank
    private String username;
    @NotBlank
    @Size(min=6)
    private String password;
}
