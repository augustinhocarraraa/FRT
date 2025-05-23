package com.frt.api.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.frt.api.service.UsuarioService;
import com.frt.api.models.dtos.usuario.UsuarioRequestDTO;
import com.frt.api.models.dtos.usuario.UsuarioResponseDTO;

@RestController
@RequestMapping("/api/cad")
public class CadController {
    
    private final UsuarioService usuarioService;

    @Autowired
    public CadController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario")
    public UsuarioResponseDTO cadastrarUsuario(@RequestBody UsuarioRequestDTO dto) {
        return usuarioService.cadastrarUsuario(dto);
    }
}