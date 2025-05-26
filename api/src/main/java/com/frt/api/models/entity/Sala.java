package com.frt.api.models.entity;

import java.util.List;

import com.frt.api.models.enums.TipoSala;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sala {
    @Id
    @Column(length = 10)
    private String numero; // Ex: "LAB-101"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSala tipo; // LABORATORIO, SALA_AULA, AUDITORIO

    @OneToMany(mappedBy = "sala")
    private List<Reserva> reservas;

    @Column(nullable = false)
    private Integer capacidade;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
}