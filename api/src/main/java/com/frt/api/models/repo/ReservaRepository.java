package com.frt.api.models.repo;

import com.frt.api.models.entity.Curso;
import com.frt.api.models.entity.Reserva;
import com.frt.api.models.entity.Sala;
import com.frt.api.models.entity.Usuario;
import com.frt.api.models.enums.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    // Verifica conflitos de horário para uma sala
    boolean existsBySalaAndDataHoraInicioLessThanAndDataHoraFimGreaterThanAndStatusNot(
        Sala sala,
        LocalDateTime dataHoraFim,
        LocalDateTime dataHoraInicio,
        StatusReserva status
    );

    // Lista reservas de um professor
    List<Reserva> findByProfessor(Usuario professor);

    // Busca reservas por sala e status
    @Query("SELECT r FROM Reserva r WHERE r.sala.numero = :salaNumero AND r.status = :status")
    List<Reserva> findBySalaNumeroAndStatus(
        @Param("salaNumero") String salaNumero,
        @Param("status") StatusReserva status
    );

    // Nova consulta para filtrar por curso e intervalo de datas
    List<Reserva> findBySala_CursoAndDataHoraInicioBetween(
        Curso curso,
        LocalDateTime inicio,
        LocalDateTime fim
    );
}