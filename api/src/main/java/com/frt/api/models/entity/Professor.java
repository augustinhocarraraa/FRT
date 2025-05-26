package com.frt.api.models.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Professor extends Usuario {

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private boolean ativo = true;

    // Relacionamento com Administrador (quem cadastrou)
    @ManyToOne
    @JoinColumn(name = "cadastrado_por_id")
    private Administrador cadastradoPor;

    @OneToMany(mappedBy = "professor")
    private List<Reserva> reservas;
}