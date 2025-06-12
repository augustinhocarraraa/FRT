// Mobile/app/src/main/java/com/frt/mobile/Salas/Data/SalaAdapter.java
package com.frt.mobile.Salas.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frt.mobile.R; // Para acessar os IDs do layout de sala_item.xml

import java.util.List;

public class SalaAdapter extends RecyclerView.Adapter<SalaAdapter.SalaViewHolder> {

    private List<Salas> salasList;
    private OnSalaActionListener listener; // Interface para lidar com cliques no botão de reserva

    // Interface para comunicação com a Activity/Fragment
    public interface OnSalaActionListener {
        void onReservarClick(Salas sala);
    }

    public SalaAdapter(List<Salas> salasList, OnSalaActionListener listener) {
        this.salasList = salasList;
        this.listener = listener;
    }

    public void updateSalas(List<Salas> newSalas) {
        this.salasList = newSalas;
        notifyDataSetChanged(); // Notifica o adapter que os dados mudaram
    }

    @NonNull
    @Override
    public SalaAdapter.SalaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sala_item, parent, false); // Infla o layout do item de sala
        return new SalaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaAdapter.SalaViewHolder holder, int position) {
        Salas sala = salasList.get(position);

        // Define os textos das TextViews com base nos dados da Sala
        holder.numeroSalaTextView.setText("Sala: " + sala.getCodigoSala()); // Supondo que Salas.getCodigoSala() retorna o número
        holder.tipoSalaTextView.setText("Tipo: " + sala.getTipoSala());     // Supondo que Salas.getTipoSala() retorna o tipo
        holder.capacidadeSalaTextView.setText("Capacidade: " + sala.getCapacidade()); // Supondo que Salas.getCapacidade() retorna a capacidade

        // Configura o listener para o botão de reserva
        holder.reservarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onReservarClick(sala); // Chama o método na Activity/Fragment
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return salasList != null ? salasList.size() : 0;
    }

    public static class SalaViewHolder extends RecyclerView.ViewHolder {
        TextView numeroSalaTextView;
        TextView tipoSalaTextView;
        TextView capacidadeSalaTextView;
        Button reservarButton; // Declarar o Button

        public SalaViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializar as views com base nos IDs em sala_item.xml
            numeroSalaTextView = itemView.findViewById(R.id.tv_numero_sala);
            tipoSalaTextView = itemView.findViewById(R.id.tv_tipo_sala);
            capacidadeSalaTextView = itemView.findViewById(R.id.tv_capacidade_sala);
            reservarButton = itemView.findViewById(R.id.btn_reservar_sala); // Inicializar o Button
        }
    }
}