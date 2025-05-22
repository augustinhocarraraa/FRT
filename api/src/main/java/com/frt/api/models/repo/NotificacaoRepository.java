package com.frt.api.models.repo;

import com.frt.api.models.entity.Notificacao;
import com.frt.api.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    
    // Notificações não lidas de um usuário
    List<Notificacao> findByUsuarioAndLidaFalse(Usuario usuario);

    // Todas as notificações ordenadas por data
    List<Notificacao> findByUsuarioOrderByDataEnvioDesc(Usuario usuario);
}