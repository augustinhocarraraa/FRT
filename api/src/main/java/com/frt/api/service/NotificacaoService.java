package com.frt.api.service;


import com.frt.api.models.entity.Notificacao;
import com.frt.api.models.entity.Reserva;
import com.frt.api.models.repo.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public void enviarNotificacaoReserva(Reserva reserva) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem(
            String.format("Reserva da sala %s pendente de aprovação", reserva.getSala().getNumero())
        );
        notificacao.setDataEnvio(LocalDateTime.now());
        notificacao.setUsuario(reserva.getProfessor());
        
        notificacaoRepository.save(notificacao);
    }
}