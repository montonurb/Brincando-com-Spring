package com.montonurb.demo_park_api.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.montonurb.demo_park_api.Enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="usernamo", nullable=false, unique=true, length=100)
    private String username;
    @Column(name="password", nullable=false, length=200)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable=false, length=25)
    private Role role;
    @Column(name="data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name="data_modificacao")
    private LocalDateTime dataModificacao;
    @Column(name="criado_por")
    private String criadoPor;
    @Column(name="modificado_por")
    private String modificadoPor;
    
}


