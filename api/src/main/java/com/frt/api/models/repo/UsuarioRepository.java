package com.frt.api.models.repo;

import com.frt.api.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para buscar por e-mail
    Optional<Usuario> findByEmail(String email);

    // Método para buscar por registro acadêmico OU e-mail
    @Query("SELECT u FROM Usuario u WHERE u.registroAcademico = :registroOuEmail OR u.email = :registroOuEmail")
    Optional<Usuario> findByRegistroAcademicoOrEmail(@Param("registroOuEmail") String registroOuEmail);

    boolean existsByRegistroAcademico(String registroAcademico);
    
    boolean existsByEmail(String email);

    Optional<Usuario> findByTokenRedefinicao(String token);
}