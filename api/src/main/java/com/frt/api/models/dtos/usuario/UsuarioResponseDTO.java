package com.frt.api.models.dtos.usuario;

import com.frt.api.models.enums.PerfilUsuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String registroAcademico,
    String email,
    PerfilUsuario perfil
) {}