package com.frt.api.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SecurityUtils {

    public static boolean validarTokenRedefinicao(LocalDateTime dataCriacao) {
        if (dataCriacao == null) return false;
        return ChronoUnit.HOURS.between(dataCriacao, LocalDateTime.now()) < 24;
    }

    // Configurações (pode ser externalizado para application.properties)
    private static final int TOKEN_REDEFINICAO_TAMANHO = 32;
    private static final long TOKEN_REDEFINICAO_EXPIRACAO_HORAS = 24;
    private static final String CARACTERES_PERMITIDOS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

    /**
     * Gera um token seguro para redefinição de senha
     */
    public static String gerarTokenRedefinicao() {
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder(TOKEN_REDEFINICAO_TAMANHO);

        for (int i = 0; i < TOKEN_REDEFINICAO_TAMANHO; i++) {
            int index = random.nextInt(CARACTERES_PERMITIDOS.length());
            token.append(CARACTERES_PERMITIDOS.charAt(index));
        }

        return token.toString();
    }

    /**
     * Valida se um token de redefinição ainda está dentro do prazo de validade
     */
    public static boolean validarTokenRedefinicao(Date dataCriacao) {
        long diferencaMillis = new Date().getTime() - dataCriacao.getTime();
        long horasExpiracao = TimeUnit.MILLISECONDS.toHours(diferencaMillis);
        return horasExpiracao < TOKEN_REDEFINICAO_EXPIRACAO_HORAS;
    }

    /**
     * Codifica dados em Base64 URL-safe
     */
    public static String codificarBase64(byte[] dados) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(dados);
    }

    /**
     * Decodifica dados em Base64 URL-safe
     */
    public static byte[] decodificarBase64(String dados) {
        return Base64.getUrlDecoder().decode(dados);
    }

    /**
     * Gera um código numérico aleatório (para 2FA)
     */
    public static String gerarCodigoNumerico(int digitos) {
        SecureRandom random = new SecureRandom();
        StringBuilder codigo = new StringBuilder(digitos);
        for (int i = 0; i < digitos; i++) {
            codigo.append(random.nextInt(10));
        }
        return codigo.toString();
    }

    /**
     * Valida força da senha (mínimo 8 caracteres, com letras e números)
     */
    public static boolean validarForcaSenha(String senha) {
        return senha != null && 
               senha.length() >= 8 && 
               senha.matches(".*[A-Za-z].*") && 
               senha.matches(".*[0-9].*");
    }
}