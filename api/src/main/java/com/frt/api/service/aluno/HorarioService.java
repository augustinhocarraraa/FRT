package com.frt.api.service.aluno;

import com.frt.api.models.entity.Aluno;
import com.frt.api.models.entity.Reserva;
import com.frt.api.models.repo.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioService {

    private final ReservaRepository reservaRepository;

    public List<Reserva> consultarHorarios(Aluno aluno, LocalDate data) {
        return reservaRepository.findBySala_CursoAndDataHoraInicioBetween(
            aluno.getCurso(),
            data.atStartOfDay(),
            data.plusDays(1).atStartOfDay()
        );
    }
}