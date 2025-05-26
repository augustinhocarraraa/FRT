package com.frt.api.models.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Adicione esta linha
import org.springframework.data.repository.query.Param;

import com.frt.api.models.entity.Aviso;
import com.frt.api.models.enums.PublicoAlvo;

public interface AvisoRepository extends JpaRepository<Aviso, Long> {
    
    // Busca avisos por público-alvo e data
    List<Aviso> findByPublicoAlvoAndDataPublicacaoBetween(
        PublicoAlvo publicoAlvo,
        LocalDateTime inicio,
        LocalDateTime fim
    );

    // Avisos recentes (últimos 7 dias)
    @Query("SELECT a FROM Aviso a WHERE a.dataPublicacao >= :dataLimite ORDER BY a.dataPublicacao DESC")
    List<Aviso> findRecentes(@Param("dataLimite") LocalDateTime dataLimite);

    List<Aviso> findByPublicoAlvoOrPublicoAlvo(String publicoAlvo1, String publicoAlvo2);
}