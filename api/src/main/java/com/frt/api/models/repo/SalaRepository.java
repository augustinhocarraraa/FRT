package com.frt.api.models.repo;

import com.frt.api.models.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, String> {
    
    // Busca salas disponíveis em um horário específico
    @Query("SELECT s FROM Sala s WHERE s NOT IN " +
           "(SELECT r.sala FROM Reserva r WHERE " +
           "r.dataHoraInicio < :dataHoraFim AND r.dataHoraFim > :dataHoraInicio AND r.status <> 'CANCELADA')")
    List<Sala> findSalasDisponiveis(
        @Param("dataHoraInicio") LocalDateTime dataHoraInicio,
        @Param("dataHoraFim") LocalDateTime dataHoraFim
    );
}