package com.frt.api.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aluno extends Usuario {
    @Column(nullable = false)
    private String turma;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}