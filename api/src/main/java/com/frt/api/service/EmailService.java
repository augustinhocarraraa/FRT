package com.frt.api.service;

public interface EmailService {
    void enviarTokenRedefinicao(String email, String token);
}