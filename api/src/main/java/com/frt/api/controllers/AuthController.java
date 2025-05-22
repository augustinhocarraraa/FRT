package com.frt.api.controllers;

import com.frt.api.models.dtos.auth.LoginDTO;
import com.frt.api.models.dtos.auth.TokenResponseDTO;
import com.frt.api.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<Void> solicitarRedefinicaoSenha(@RequestParam String email) {
        authService.solicitarRedefinicaoSenha(email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/nova-senha")
    public ResponseEntity<Void> redefinirSenha(
            @RequestParam String token,
            @RequestParam String novaSenha) {
        authService.redefinirSenha(token, novaSenha);
        return ResponseEntity.noContent().build();
    }
}