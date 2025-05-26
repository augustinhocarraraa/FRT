package com.frt.api.models.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo; // Ex: "MAT-101"

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int cargaHoraria;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Professor> professores;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
