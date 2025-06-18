package com.frt.mobile.Salas.Presetation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.frt.mobile.R; // Certifique-se de que este import está correto para o seu projeto
import com.frt.mobile.Salas.Data.SalaAdapter;
import com.frt.mobile.Salas.Data.Salas;
import com.frt.mobile.Salas.Utils.SalaService; // Importe o serviço Retrofit
import com.frt.mobile.Shared.Utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalasActivity extends AppCompatActivity implements SalaAdapter.OnItemClickListener {

    // Declaração dos elementos da UI
    private RecyclerView recyclerViewSalas;
    private ProgressBar progressBarSalas;
    private SalaAdapter salaAdapter;
    private List<Salas> listaSalas; // Lista que armazenará os objetos Salas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas); // Define o layout da Activity

        // Encontra e associa os elementos da UI com suas IDs no layout
        recyclerViewSalas = findViewById(R.id.salas_recycler_view);
        progressBarSalas = findViewById(R.id.progress_bar_salas);

        recyclerViewSalas.setLayoutManager(new LinearLayoutManager(this));
        listaSalas = new ArrayList<>(); // Inicializa a lista vazia

// CORRIGIDO: Passa 'this' (que é o Context da Activity) e a 'listaSalas'
        salaAdapter = new SalaAdapter(this, listaSalas);
        recyclerViewSalas.setAdapter(salaAdapter);

// Define o listener para cliques nos itens da lista.
// 'this' é passado porque SalasActivity implementa SalaAdapter.OnItemClickListener
        salaAdapter.setOnItemClickListener(this);

// Carrega as salas da API
        carregarSalas();
    }

    /**
     * Carrega a lista de salas disponíveis da API.
     */
    private void carregarSalas() {
        // Exibe a ProgressBar e oculta o RecyclerView enquanto carrega
        progressBarSalas.setVisibility(View.VISIBLE);
        recyclerViewSalas.setVisibility(View.GONE);

        SalaService salaService = RetrofitClient.getRetrofitInstance().create(SalaService.class);
        Call<List<Salas>> call = salaService.getSalas(); // Faz a chamada à API para obter a lista de salas

        call.enqueue(new Callback<List<Salas>>() {
            @Override
            public void onResponse(Call<List<Salas>> call, Response<List<Salas>> response) {
                // Oculta a ProgressBar e exibe o RecyclerView após a resposta
                progressBarSalas.setVisibility(View.GONE);
                recyclerViewSalas.setVisibility(View.VISIBLE);

                if (response.isSuccessful() && response.body() != null) {
                    // Limpa a lista existente e adiciona as novas salas
                    listaSalas.clear();
                    listaSalas.addAll(response.body());
                    salaAdapter.notifyDataSetChanged(); // Notifica o adaptador que os dados mudaram

                    if (listaSalas.isEmpty()) {
                        Toast.makeText(SalasActivity.this, "Nenhuma sala disponível no momento.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    // Log de erro e mensagem para o usuário
                    Toast.makeText(SalasActivity.this, "Erro ao carregar salas: " + response.code(), Toast.LENGTH_LONG).show();
                    // Opcional: Mostrar uma mensagem de "sem salas" ou tentar novamente
                }
            }

            @Override
            public void onFailure(Call<List<Salas>> call, Throwable t) {
                // Oculta a ProgressBar e exibe o RecyclerView (mesmo em caso de falha para que o layout não fique vazio)
                progressBarSalas.setVisibility(View.GONE);
                recyclerViewSalas.setVisibility(View.VISIBLE);

                // Log de exceção e mensagem para o usuário
                Toast.makeText(SalasActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace(); // Imprime o stack trace para depuração
            }
        });
    }

    /**
     * Método de callback para cliques em itens do RecyclerView.
     * Implementa a interface SalaAdapter.OnItemClickListener.
     * @param sala A sala que foi clicada.
     */
    @Override
    public void onItemClick(Salas sala) {
        // Cria um Intent para iniciar a ReservaActivity
        Intent intent = new Intent(SalasActivity.this, ReservasActivity.class);
        // Passa o nome da sala para a ReservaActivity
        intent.putExtra("nome_sala", sala.getCodigoSala()); // Supondo que getCodigoSala() retorne o nome da sala
        // Opcional: passar o objeto completo da sala se for Parcelable/Serializable
        // intent.putExtra("sala_objeto", sala);
        startActivity(intent);
        Toast.makeText(this, "Sala selecionada: " + sala.getCodigoSala(), Toast.LENGTH_SHORT).show();
    }
}
