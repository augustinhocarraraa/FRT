package com.frt.api.models.dtos.reserva;

import java.time.LocalDateTime;

public record ReservaResponseDTO(
    Long id,
    String salaNumero,
    String professorNome,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim,
    String status,
    String justificativa
) {}