package com.frt.mobile.Salas.Data;

public class Salas {
    private int bloco;
    private String codigoSala;
    private String tipoSala; // Assuming tipoSala is a String, based on SalaAdapter usage
    private int capacidade; // Assuming capacidade is an int, based on SalaAdapter usage

    // Add a constructor if needed, e.g.,
    public Salas(int bloco, String codigoSala, String tipoSala, int capacidade) {
        this.bloco = bloco;
        this.codigoSala = codigoSala;
        this.tipoSala = tipoSala;
        this.capacidade = capacidade;
    }

    // Add getters for the fields used in SalaAdapter
    public String getCodigoSala() {
        return codigoSala;
    }

    public String getTipoSala() {
        // You'll need to define how 'tipoSala' is determined or passed to this object.
        // For now, returning a placeholder.
        return tipoSala;
    }

    public int getCapacidade() {
        return capacidade;
    }

    // You can also add setters if you need to modify these values after object creation
    public void setBloco(int bloco) {
        this.bloco = bloco;
    }

    public int getBloco() {
        return this.bloco;
    }

    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}