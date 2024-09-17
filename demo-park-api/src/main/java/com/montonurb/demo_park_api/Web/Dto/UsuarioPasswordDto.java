package com.montonurb.demo_park_api.Web.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioPasswordDto {
    @NotBlank
    @Size(min=6)
    private String newPassword;
    @NotBlank
    @Size(min=6)
    private String oldPassword;
    @NotBlank
    @Size(min=6)
    private String confirmPassword;
}
