// Mobile/app/src/main/java/com/frt/mobile/Login/Data/LoginRequest.java
package com.frt.mobile.Login.Data;

public class Login {
    private String email; // ou 'username', dependendo do que sua API espera
    private String senha; // ou 'password'

    public Login(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}