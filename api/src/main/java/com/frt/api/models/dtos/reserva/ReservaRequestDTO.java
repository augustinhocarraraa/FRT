package com.frt.api.models.dtos.reserva;


import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReservaRequestDTO(
    @NotBlank(message = "Número da sala é obrigatório")
    String salaNumero,

    @NotNull(message = "Data/hora de início é obrigatória")
    @Future(message = "Data deve ser futura")
    LocalDateTime dataHoraInicio,

    @NotNull(message = "Data/hora de fim é obrigatória")
    @Future(message = "Data deve ser futura")
    LocalDateTime dataHoraFim,

    @NotBlank(message = "Justificativa é obrigatória")
    @Size(max = 500, message = "Justificativa muito longa")
    String justificativa
) {}