package com.frt.api.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.frt.api.service.admin.AvisoService;
import com.frt.api.models.dtos.aviso.AvisoResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/avisos")
public class AvisoController {
    private final AvisoService avisoService;

    @Autowired
    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    // Endpoint para o usuário receber notificações/avisos
    @GetMapping("/usuario/{publicoAlvo}")
    public List<AvisoResponseDTO> listarAvisosParaUsuario(@PathVariable String publicoAlvo) {
        return avisoService.buscarAvisosPorPublicoAlvo(publicoAlvo);
    }
}