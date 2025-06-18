package com.frt.mobile.Salas.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frt.mobile.R; // Certifique-se de que este import está correto

import java.util.List;

public class SalaAdapter extends RecyclerView.Adapter<SalaAdapter.SalaViewHolder> {

    private Context context;
    private List<Salas> listaSalas;
    private OnItemClickListener listener; // Variável para armazenar o listener

    // 1. Definição CORRETA da interface OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(Salas sala);
    }

    // 2. Método para definir o listener (chamado de SalasActivity)
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public SalaAdapter(Context context, List<Salas> listaSalas) {
        this.context = context;
        this.listaSalas = listaSalas;
    }

    @NonNull
    @Override
    public SalaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sala_item, parent, false);
        return new SalaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaViewHolder holder, int position) {
        final Salas currentSala = listaSalas.get(position);

        holder.tvCodigoSala.setText("Código: " + currentSala.getCodigoSala());
        holder.tvTipoSala.setText("Tipo: " + currentSala.getTipoSala());
        holder.tvCapacidade.setText("Capacidade: " + currentSala.getCapacidade());

        // Configura o clique no item COMPLETO da view (o CardView ou LinearLayout pai no sala_item.xml)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(currentSala); // Notifica o clique do item
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaSalas.size();
    }

    public static class SalaViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCodigoSala;
        public TextView tvTipoSala;
        public TextView tvCapacidade;

        public SalaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoSala = itemView.findViewById(R.id.tv_numero_sala);
            tvTipoSala = itemView.findViewById(R.id.tv_tipo_sala);
            tvCapacidade = itemView.findViewById(R.id.tv_capacidade);
        }
    }
}
