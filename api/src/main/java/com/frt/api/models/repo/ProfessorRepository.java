package com.frt.api.models.repo;

import com.frt.api.models.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    // Busca professor por e-mail (herdado de Usuario)
    Optional<Professor> findByEmail(String email);

    // Busca professores por departamento (usando relacionamento com Administrador)
    @Query("SELECT p FROM Professor p WHERE p.cadastradoPor.departamento = :departamento")
    List<Professor> findByDepartamento(@Param("departamento") String departamento);

    // Busca professores que não têm reservas em um período específico
    @Query("SELECT p FROM Professor p WHERE p NOT IN " +
           "(SELECT r.professor FROM Reserva r WHERE " +
           "r.dataHoraInicio < :dataFim AND r.dataHoraFim > :dataInicio)")
    List<Professor> findProfessoresDisponiveis(
        @Param("dataInicio") LocalDateTime dataInicio,
        @Param("dataFim") LocalDateTime dataFim
    );

    // Contagem de professores por status (para dashboard administrativo)
    @Query("SELECT COUNT(p) FROM Professor p WHERE p.ativo = :ativo")
    long countByStatus(@Param("ativo") boolean ativo);
}