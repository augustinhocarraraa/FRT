package com.frt.api.models.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Administrador extends Usuario {
    @Column(nullable = false)
    private String departamento;

    // Relacionamento com avisos
    @OneToMany(mappedBy = "administrador")
    private List<Aviso> avisos;
}