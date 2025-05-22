package com.frt.api.models.dtos.error;

import java.time.LocalDateTime;

public record ErrorDTO(
    String codigo,
    String mensagem,
    LocalDateTime timestamp
) {}