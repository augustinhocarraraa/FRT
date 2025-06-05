package com.frt.mobile.Cadastro.Data;

public class Cadastro {
    String nome;
    String registroAcademico;
    String email;
    String perfil;

    public void SetName(String nome){
        this.nome = nome;
    }

    public String getName(){
        return this.nome;
    }

    public void setEmail(){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPerfil(String perfil){
        this.perfil = perfil;
    }

    public String getPerfil(){
        return this.perfil;
    }

    public void setRegistroAcademico(String registroAcademico){
        this.registroAcademico = registroAcademico;
    }

    public String getRegistroAcademico(){
        return this.registroAcademico;
    }


}