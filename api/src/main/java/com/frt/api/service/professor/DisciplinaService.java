package com.frt.api.service.professor;

import com.frt.api.models.entity.Disciplina;
import com.frt.api.models.repo.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public List<Disciplina> listarPorCurso(Long cursoId) {
        return disciplinaRepository.findByCursoId(cursoId);
    }

    public Disciplina criarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }
}