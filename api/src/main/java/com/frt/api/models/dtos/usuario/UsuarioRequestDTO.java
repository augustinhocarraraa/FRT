package com.frt.api.models.dtos.usuario;

import com.frt.api.models.enums.PerfilUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record UsuarioRequestDTO(
    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotBlank(message = "Registro acadêmico é obrigatório")
    @Size(min = 6, max = 6, message = "Registro deve ter 6 dígitos")
    String registroAcademico,

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    String email,

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    String senha,

    @NotNull(message = "Perfil é obrigatório")
    PerfilUsuario perfil
) {}