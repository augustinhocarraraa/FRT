package com.frt.mobile.Login.Data;

public class LoginResponse {
    private String token; // Corresponde ao campo 'token' do TokenResponseDTO
    private String tipo;  // Corresponde ao campo 'tipo' do TokenResponseDTO (ex: "Bearer")

    public LoginResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}