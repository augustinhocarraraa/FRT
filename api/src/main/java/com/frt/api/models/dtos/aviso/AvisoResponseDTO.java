package com.frt.api.models.dtos.aviso;

import java.time.LocalDateTime;

public record AvisoResponseDTO(
    Long id,
    String titulo,
    String conteudo,
    String publicoAlvo,
    String autor,
    LocalDateTime dataPublicacao
) {}