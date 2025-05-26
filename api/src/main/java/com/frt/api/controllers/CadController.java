package com.frt.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frt.api.models.dtos.usuario.UsuarioRequestDTO;
import com.frt.api.models.dtos.usuario.UsuarioResponseDTO;
import com.frt.api.service.UsuarioService;

@RestController
@RequestMapping("/api/cad")
public class CadController {
    
    private final UsuarioService usuarioService;

    public CadController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario")
    public UsuarioResponseDTO cadastrarUsuario(@RequestBody UsuarioRequestDTO dto) {
        return usuarioService.cadastrarUsuario(dto);
    }
}