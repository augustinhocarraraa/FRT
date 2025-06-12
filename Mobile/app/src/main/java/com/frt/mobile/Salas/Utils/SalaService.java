// Mobile/app/src/main/java/com/frt/mobile/Salas/Utils/SalaService.java
package com.frt.mobile.Salas.Utils;

import com.frt.mobile.Salas.Data.Reserva; // Para a resposta da reserva, se for o caso
import com.frt.mobile.Salas.Data.Salas; // Importe a classe Salas

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Headers; // Para Authorization Header (se já não estiver no RetrofitClient)

public interface SalaService {

    // Endpoint para buscar todas as salas disponíveis
    // Adapte o endpoint conforme sua API (ex: "/salas/disponiveis")
    @GET("api/salas") // Exemplo: Sua API deve ter um endpoint para listar salas
    Call<List<Salas>> getSalas();

    // Endpoint para fazer uma reserva de sala (exemplo, você precisará adaptar os parâmetros)
    // O ReservaRequestDTO deve ser o DTO que sua API espera para criar uma reserva.
    @POST("api/reservas") // Exemplo: Sua API deve ter um endpoint para criar reservas
    Call<Reserva> createReserva(@Body Reserva reservaRequest);
}