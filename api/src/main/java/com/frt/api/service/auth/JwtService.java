package com.frt.api.service.auth;

import com.frt.api.models.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Geração padrão com claims fixos (para login)
    public String gerarToken(Usuario usuario) {
        Map<String, Object> claims = Map.of("perfil", usuario.getPerfil().name());
        return gerarTokenComClaims(claims, usuario.getEmail(), expiration);
    }

    // Geração com claims personalizados e tempo customizado (redefinição de senha)
    public String gerarTokenComExpiracao(Map<String, Object> claims, Usuario usuario, int minutosValidade) {
        long expMillis = minutosValidade * 60L * 1000L;
        return gerarTokenComClaims(claims, usuario.getEmail(), expMillis);
    }

    // Método interno que faz a geração de token
    private String gerarTokenComClaims(Map<String, Object> claims, String subject, long tempoExpiracaoMillis) {
        Date agora = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(agora)
                .setExpiration(new Date(agora.getTime() + tempoExpiracaoMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validação simples do token (se não expirou e está bem formado)
    public boolean tokenValido(String token) {
        try {
            return !isTokenExpirado(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    public <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpirado(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extrairClaim(token, Claims::getExpiration);
    }

    public boolean validarToken(String token, Usuario usuario) {
        final String email = getEmailFromToken(token);
        return (email.equals(usuario.getEmail()) && !isTokenExpirado(token));
    }
}
