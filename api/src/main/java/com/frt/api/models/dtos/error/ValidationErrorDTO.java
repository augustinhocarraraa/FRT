package com.frt.api.models.dtos.error;

public record ValidationErrorDTO(
    String campo,
    String mensagem
) {}