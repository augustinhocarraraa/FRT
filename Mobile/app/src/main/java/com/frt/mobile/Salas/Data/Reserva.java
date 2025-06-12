// Mobile/app/src/main/java/com/frt/mobile/Salas/Data/Reserva.java
package com.frt.mobile.Salas.Data;

// Não é necessário importar java.time.LocalDateTime diretamente aqui se você for armazená-los como String.
// Se você for usar LocalDateTime no Mobile, precisará de bibliotecas como ThreeTenABP ou API mínima 26+
// import java.time.LocalDateTime;

public class Reserva {
    private Long id;
    private String salaNumero; // Corresponde a 'salaNumero' do DTO
    private String professorNome; // Corresponde a 'professorNome' do DTO
    private String dataHoraInicio; // Armazenado como String, fácil de parsear da API
    private String dataHoraFim;    // Armazenado como String, fácil de parsear da API
    private String status;         // Corresponde a 'status' do DTO
    private String justificativa;  // Corresponde a 'justificativa' do DTO

    // Construtor completo para criar objetos Reserva a partir dos dados da API
    public Reserva(Long id, String salaNumero, String professorNome,
                   String dataHoraInicio, String dataHoraFim,
                   String status, String justificativa) {
        this.id = id;
        this.salaNumero = salaNumero;
        this.professorNome = professorNome;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.status = status;
        this.justificativa = justificativa;
    }

    // Getters para todos os atributos
    public Long getId() {
        return id;
    }

    public String getSalaNumero() {
        return salaNumero;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public String getDataHoraFim() {
        return dataHoraFim;
    }

    public String getStatus() {
        return status;
    }

    public String getJustificativa() {
        return justificativa;
    }

    // Opcional: Setters para todos os atributos (se você precisar modificá-los após a criação)
    public void setId(Long id) {
        this.id = id;
    }

    public void setSalaNumero(String salaNumero) {
        this.salaNumero = salaNumero;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}