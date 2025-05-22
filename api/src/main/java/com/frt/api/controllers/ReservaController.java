package com.frt.api.controllers;


import com.frt.api.service.professor.ReservaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frt.api.models.dtos.reserva.ReservaRequestDTO;
import com.frt.api.models.dtos.reserva.ReservaResponseDTO;
import com.frt.api.models.entity.Usuario;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // Endpoint para reservar uma sala
    @PostMapping
    public ReservaResponseDTO reservarSala(@RequestBody ReservaRequestDTO dto, @RequestAttribute Usuario usuario) {
        // Aqui, 'usuario' deve ser recuperado do contexto de autenticação (ajuste conforme sua autenticação)
        return reservaService.criarReserva(dto, usuario);
    }
}