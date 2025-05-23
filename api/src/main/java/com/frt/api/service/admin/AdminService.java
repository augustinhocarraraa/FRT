package com.frt.api.service.admin;

import com.frt.api.models.entity.Administrador;
import com.frt.api.models.entity.Professor;
import com.frt.api.models.repo.ProfessorRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ProfessorRepository professorRepository;

    public Professor cadastrarProfessor(Professor professor, Administrador administrador) {
        professor.setCadastradoPor(administrador);
        return professorRepository.save(professor);
    }

    public void removerProfessor(Long id) {
        professorRepository.deleteById(id);
    }
}