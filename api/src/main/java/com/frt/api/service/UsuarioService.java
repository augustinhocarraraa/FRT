package com.frt.api.service;

import com.frt.api.models.dtos.usuario.UsuarioRequestDTO;
import com.frt.api.models.dtos.usuario.UsuarioResponseDTO;
import com.frt.api.models.entity.Usuario;
import com.frt.api.models.repo.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        // TODO: Implement the user registration logic here
        return null;
    }

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByRegistroAcademico(dto.registroAcademico())) {
            throw new RuntimeException("Registro acadêmico já cadastrado");
        }

        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setRegistroAcademico(dto.registroAcademico());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setPerfil(dto.perfil());

        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(
            salvo.getId(),
            salvo.getNome(),
            salvo.getRegistroAcademico(),
            salvo.getEmail(),
            salvo.getPerfil()
        );
    }
}