package com.frt.api.service.admin;

import com.frt.api.models.dtos.aviso.AvisoResponseDTO;
import com.frt.api.models.entity.Aviso;
import com.frt.api.models.repo.AvisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvisoService {

    private final AvisoRepository avisoRepository;

    public List<AvisoResponseDTO> buscarAvisosPorPublicoAlvo(String publicoAlvo) {
        List<Aviso> avisos = avisoRepository.findByPublicoAlvoOrPublicoAlvo("TODOS", publicoAlvo.toUpperCase());
        return avisos.stream()
                .map(aviso -> new AvisoResponseDTO(
                        aviso.getId(),
                        aviso.getTitulo(),
                        aviso.getConteudo(),
                        aviso.getPublicoAlvo().name(),
                        aviso.getAdministrador().getNome(),
                        aviso.getDataPublicacao()
                ))
                .collect(Collectors.toList());
    }
}