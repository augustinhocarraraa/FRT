package com.frt.api.models.repo;

import com.frt.api.models.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    // Busca disciplinas por curso
    List<Disciplina> findByCursoId(Long cursoId);

    // Busca disciplinas por nome (case insensitive)
    List<Disciplina> findByNomeContainingIgnoreCase(String nome);

    // Busca disciplinas com carga horária maior que X
    @Query("SELECT d FROM Disciplina d WHERE d.cargaHoraria > :minCargaHoraria")
    List<Disciplina> findComCargaHorariaMinima(@Param("minCargaHoraria") int minCargaHoraria);

    // Busca disciplinas por professor responsável
    @Query("SELECT d FROM Disciplina d JOIN d.professores p WHERE p.id = :professorId")
    List<Disciplina> findByProfessorId(@Param("professorId") Long professorId);
}