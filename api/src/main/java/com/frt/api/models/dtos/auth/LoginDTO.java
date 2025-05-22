package com.frt.api.models.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    @NotBlank(message = "Registro acadêmico ou e-mail é obrigatório")
    String registroOuEmail,

    @NotBlank(message = "Senha é obrigatória")
    String senha
) {}