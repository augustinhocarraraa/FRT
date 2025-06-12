// Mobile/app/src/main/java/com/frt/mobile/Salas/Data/ReservaRequestDTO.java
package com.frt.mobile.Salas.Data;

// Não é necessário importar java.time.LocalDateTime diretamente aqui, use String
// import java.time.LocalDateTime;

public class ReservaRequestDTO {
    // Esses campos devem corresponder ao que sua API espera na requisição de reserva
    private Long idSala; // ID da sala que está sendo reservada
    private Long idProfessor; // ID do professor que está fazendo a reserva
    private String dataHoraInicio; // Ex: "2025-06-12T10:00:00"
    private String dataHoraFim;    // Ex: "2025-06-12T11:00:00"
    private String justificativa;

    public ReservaRequestDTO(Long idSala, Long idProfessor, String dataHoraInicio, String dataHoraFim, String justificativa) {
        this.idSala = idSala;
        this.idProfessor = idProfessor;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.justificativa = justificativa;
    }

    // Getters
    public Long getIdSala() {
        return idSala;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public String getDataHoraFim() {
        return dataHoraFim;
    }

    public String getJustificativa() {
        return justificativa;
    }

    // Setters (opcional, mas pode ser útil)
    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}