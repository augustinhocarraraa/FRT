package com.frt.api.service.auth;

import com.frt.api.models.dtos.auth.LoginDTO;
import com.frt.api.models.dtos.auth.TokenResponseDTO;
import com.frt.api.models.entity.Usuario;
import com.frt.api.models.repo.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDTO login(LoginDTO dto) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.registroOuEmail(), dto.senha())
        );

        Usuario usuario = usuarioRepository.findByRegistroAcademicoOrEmail(dto.registroOuEmail())
            .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas"));

        String token = jwtService.gerarToken(usuario);

        return new TokenResponseDTO(
            token,
            "Bearer",
            usuario.getPerfil().name(),
            usuario.getId(),
            usuario.getNome()
        );
    }

    public void solicitarRedefinicaoSenha(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário com e-mail não encontrado"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("tipo", "redefinicao-senha");
        claims.put("email", usuario.getEmail());

        String token = jwtService.gerarTokenComExpiracao(claims, usuario, 15); // 15 minutos, por exemplo

        // Aqui você normalmente enviaria o token por e-mail
        // Por enquanto, simule com um log (ou implemente EmailService futuramente)
        System.out.println("Token de redefinição de senha para " + email + ": " + token);
    }

    @Transactional
    public void redefinirSenha(String token, String novaSenha) {
        if (!jwtService.tokenValido(token)) {
            throw new IllegalArgumentException("Token inválido ou expirado");
        }

        String email = jwtService.extrairClaim(token, claims -> claims.get("email").toString());
        String tipo = jwtService.extrairClaim(token, claims -> claims.get("tipo").toString());

        if (!"redefinicao-senha".equals(tipo)) {
            throw new IllegalArgumentException("Token não autorizado para redefinição de senha");
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }
}
