package com.frt.mobile.Login.Data;

// Importe Usuario se você for aninhar o objeto Usuario completo
// import com.frt.mobile.models.entity.Usuario; // Adapte o caminho do pacote

public class LoginResponse {
    private String token; // Exemplo: token de autenticação
    private String tipo; // Exemplo: Bearer
    private String perfilUsuario; // NOVO CAMPO: Para armazenar o perfil (ex: "PROFESSOR", "ALUNO")
    // Ou se a API retornar um objeto Usuario completo na resposta:
    // private Usuario usuario;

    // Construtor vazio
    public LoginResponse() {}

    // Getters e Setters
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

    public String getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(String perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    // Se estiver aninhando um objeto Usuario:
    // public Usuario getUsuario() { return usuario; }
    // public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
