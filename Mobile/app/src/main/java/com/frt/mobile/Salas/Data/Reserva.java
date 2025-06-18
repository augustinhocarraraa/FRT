package com.frt.mobile.Salas.Data;

public class Reserva {
    // Campos da reserva (adicione/ajuste conforme sua API)
    private String id;
    private String salaId; // Adicionado para resolver 'getSalaId'
    private String professorId; // Adicionado para resolver 'getProfessorId'
    private String dataHoraInicio;
    private String dataHoraFim;
    private String status;
    private String justificativa;

    // Construtor vazio (necessário para Retrofit/Gson)
    public Reserva() {
    }

    // Construtor com todos os campos (opcional, mas útil)
    public Reserva(String id, String salaId, String professorId, String dataHoraInicio, String dataHoraFim, String status, String justificativa) {
        this.id = id;
        this.salaId = salaId;
        this.professorId = professorId;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.status = status;
        this.justificativa = justificativa;
    }

    // Getters e Setters para todos os campos

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}
