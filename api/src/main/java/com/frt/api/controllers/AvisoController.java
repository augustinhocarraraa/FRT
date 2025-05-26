package com.frt.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frt.api.models.dtos.aviso.AvisoResponseDTO;
import com.frt.api.service.admin.AvisoService;

@RestController
@RequestMapping("/api/avisos")
public class AvisoController {
    private final AvisoService avisoService;

    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    // Endpoint para o usuário receber notificações/avisos
    @GetMapping("/usuario/{publicoAlvo}")
    public List<AvisoResponseDTO> listarAvisosParaUsuario(@PathVariable String publicoAlvo) {
        return avisoService.buscarAvisosPorPublicoAlvo(publicoAlvo);
    }
}