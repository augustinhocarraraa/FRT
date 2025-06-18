package com.frt.mobile.Salas.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frt.mobile.R; // Certifique-se de que este import está correto

import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {

    private List<Reserva> reservasList;
    private OnReservaActionListener listener;

    // Interface para comunicação com a Activity/Fragment
    public interface OnReservaActionListener {
        void onActionButtonClick(Reserva reserva);
    }

    public ReservaAdapter(List<Reserva> reservasList, OnReservaActionListener listener) {
        this.reservasList = reservasList;
        this.listener = listener;
    }

    public void updateReservas(List<Reserva> newReservas) {
        this.reservasList = newReservas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReservaAdapter.ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // CORRIGIDO: Agora infla o layout do item de reserva, não o da activity
        View view = inflater.inflate(R.layout.reserva_item, parent, false);
        return new ReservaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaAdapter.ReservaViewHolder holder, int position) {
        Reserva reserva = reservasList.get(position);

        // CORRIGIDO: Usando getSalaId() e getProfessorId() de Reserva.java
        holder.nomeSalaTextView.setText("Sala: " + reserva.getSalaId()); // Usando getSalaId()
        holder.professorNomeTextView.setText("Professor: " + reserva.getProfessorId()); // Usando getProfessorId()
        holder.dataHoraInicioTextView.setText("Início: " + reserva.getDataHoraInicio());
        holder.dataHoraFimTextView.setText("Fim: " + reserva.getDataHoraFim());
        holder.statusTextView.setText("Status: " + reserva.getStatus());
        holder.justificativaTextView.setText("Justificativa: " + reserva.getJustificativa());

        // Configurar o listener para o botão de ação
        holder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onActionButtonClick(reserva); // Chama o método na Activity/Fragment
                }
            }
        });

        // Opcional: Alterar o texto do botão com base no status da reserva
        if ("PENDENTE".equalsIgnoreCase(reserva.getStatus())) { // Adapte para os status da sua API
            holder.actionButton.setText("Aprovar/Rejeitar");
        } else if ("APROVADA".equalsIgnoreCase(reserva.getStatus())) {
            holder.actionButton.setText("Ver Detalhes");
        } else {
            holder.actionButton.setText("Detalhes/Ação");
        }
    }

    @Override
    public int getItemCount() {
        return reservasList != null ? reservasList.size() : 0;
    }

    public static class ReservaViewHolder extends RecyclerView.ViewHolder {
        TextView nomeSalaTextView;
        TextView professorNomeTextView;
        TextView dataHoraInicioTextView;
        TextView dataHoraFimTextView;
        TextView statusTextView;
        TextView justificativaTextView;
        Button actionButton;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            // CORRIGIDO: Usando tv_nome_sala conforme definido em reserva_item.xml
            nomeSalaTextView = itemView.findViewById(R.id.tv_nome_sala);
            professorNomeTextView = itemView.findViewById(R.id.tv_professor_nome);
            dataHoraInicioTextView = itemView.findViewById(R.id.tv_data_hora_inicio);
            dataHoraFimTextView = itemView.findViewById(R.id.tv_data_hora_fim);
            statusTextView = itemView.findViewById(R.id.tv_status);
            justificativaTextView = itemView.findViewById(R.id.tv_justificativa);
            actionButton = itemView.findViewById(R.id.btn_action_reserva);
        }
    }
}
