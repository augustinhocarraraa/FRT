package com.frt.api.models.entity;


import java.time.LocalDateTime;
import java.util.List;

import com.frt.api.models.enums.PerfilUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToMany;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;  // Campo movido para a classe base
    
    @Column(unique = true, nullable = false, length = 6)
    private String registroAcademico; // 6 dígitos

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha; // Criptografada com SHA-256

    @Column(name = "token_redefinicao")
    private String tokenRedefinicao;

    @Column(name = "token_data")
    private LocalDateTime tokenData;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfil; // ALUNO, PROFESSOR, ADMIN

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes;
}