package com.frt.api.service.professor;

import com.frt.api.models.dtos.reserva.ReservaRequestDTO;
import com.frt.api.models.dtos.reserva.ReservaResponseDTO;
import com.frt.api.models.entity.Professor;
import com.frt.api.models.entity.Reserva;
import com.frt.api.models.entity.Sala;
import com.frt.api.models.entity.Usuario;
import com.frt.api.models.enums.StatusReserva;
import com.frt.api.models.repo.ReservaRepository;
import com.frt.api.models.repo.SalaRepository;
import com.frt.api.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;

    @Transactional
public ReservaResponseDTO criarReserva(ReservaRequestDTO dto, Usuario professor) {
    Sala sala = salaRepository.findById(dto.salaNumero())
        .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

    // Verificação de conflito
    boolean conflito = reservaRepository.existsBySalaAndDataHoraInicioLessThanAndDataHoraFimGreaterThanAndStatusNot(
        sala,
        dto.dataHoraFim(),
        dto.dataHoraInicio(),
        StatusReserva.CANCELADA
    );

    if (conflito) {
        throw new ConflictException("Sala já reservada neste horário");
    }

    Reserva reserva = new Reserva();
    reserva.setSala(sala);
    reserva.setProfessor((Professor) professor);
    reserva.setDataHoraInicio(dto.dataHoraInicio());
    reserva.setDataHoraFim(dto.dataHoraFim());
    reserva.setJustificativa(dto.justificativa()); // Agora funciona!
    reserva.setStatus(StatusReserva.PENDENTE);

    Reserva salva = reservaRepository.save(reserva);
    
    return convertToDto(salva);
}

private ReservaResponseDTO convertToDto(Reserva reserva) {
    return new ReservaResponseDTO(
        reserva.getId(),
        reserva.getSala().getNumero(),
        reserva.getProfessor().getNome(),
        reserva.getDataHoraInicio(),
        reserva.getDataHoraFim(),
        reserva.getStatus().name(),
        reserva.getJustificativa() // Agora funciona!
    );
}
}