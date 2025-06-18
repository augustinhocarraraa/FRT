package com.frt.mobile.Salas.Presetation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.frt.mobile.R; // Certifique-se de que este import está correto para o seu projeto
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReservasActivity extends AppCompatActivity {

    // Declaração dos elementos da UI
    private TextView tvSalaSelecionada;
    private TextInputEditText etProfessorNome;
    private TextInputEditText etDataReserva;
    private TextInputEditText etHoraInicio;
    private TextInputEditText etHoraFim;
    private TextInputEditText etJustificativa;
    private Button btnCancelarReserva;
    private Button btnConfirmarReserva;
    private ProgressBar progressBarReserva;

    // Objeto Calendar para gerenciar data e hora
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout da Activity a partir do arquivo XML
        setContentView(R.layout.activity_reserva);

        // Inicializa o Calendar com a data e hora atuais
        calendar = Calendar.getInstance();

        // Encontra e associa os elementos da UI com suas IDs no layout
        tvSalaSelecionada = findViewById(R.id.tv_sala_selecionada);
        etProfessorNome = findViewById(R.id.et_professor_nome);
        etDataReserva = findViewById(R.id.et_data_reserva);
        etHoraInicio = findViewById(R.id.et_hora_inicio);
        etHoraFim = findViewById(R.id.et_hora_fim);
        etJustificativa = findViewById(R.id.et_justificativa);
        btnCancelarReserva = findViewById(R.id.btn_cancelar_reserva);
        btnConfirmarReserva = findViewById(R.id.btn_confirmar_reserva);

        // Opcional: Receber dados da sala selecionada de uma Activity anterior
        // Por exemplo, se você passou o nome da sala de SalasActivity para ReservaActivity
        if (getIntent().hasExtra("nome_sala")) {
            String nomeSala = getIntent().getStringExtra("nome_sala");
            tvSalaSelecionada.setText("Sala Selecionada: " + nomeSala);
        } else {
            tvSalaSelecionada.setText("Sala Selecionada: N/A");
        }


        // Configura o DatePickerDialog para o campo de data
        etDataReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Configura o TimePickerDialog para o campo de hora de início
        etHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(etHoraInicio);
            }
        });

        // Configura o TimePickerDialog para o campo de hora de fim
        etHoraFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(etHoraFim);
            }
        });

        // Configura o listener para o botão de cancelar
        btnCancelarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implemente a lógica para cancelar a reserva ou voltar à tela anterior
                finish(); // Simplesmente fecha a Activity
                Toast.makeText(ReservasActivity.this, "Reserva cancelada.", Toast.LENGTH_SHORT).show();
            }
        });

        // Configura o listener para o botão de confirmar reserva
        btnConfirmarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o método para lidar com a confirmação da reserva
                confirmarReserva();
            }
        });
    }

    /**
     * Exibe um DatePickerDialog para seleção de data.
     */
    private void showDatePicker() {
        new DatePickerDialog(ReservasActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Atualiza o objeto Calendar com a data selecionada
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        // Atualiza o campo de texto da data
                        updateDateEditText(etDataReserva);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    /**
     * Exibe um TimePickerDialog para seleção de hora.
     * @param targetEditText O TextInputEditText que receberá a hora selecionada.
     */
    private void showTimePicker(final TextInputEditText targetEditText) {
        new TimePickerDialog(ReservasActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                        // Atualiza o objeto Calendar com a hora selecionada
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        // Atualiza o campo de texto da hora
                        updateTimeEditText(targetEditText);
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true) // true para formato 24 horas, false para AM/PM
                .show();
    }

    /**
     * Formata a data do Calendar e define no TextInputEditText.
     * @param targetEditText O TextInputEditText para atualizar.
     */
    private void updateDateEditText(TextInputEditText targetEditText) {
        String dateFormat = "dd/MM/yyyy"; // Formato da data
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        targetEditText.setText(sdf.format(calendar.getTime()));
    }

    /**
     * Formata a hora do Calendar e define no TextInputEditText.
     * @param targetEditText O TextInputEditText para atualizar.
     */
    private void updateTimeEditText(TextInputEditText targetEditText) {
        String timeFormat = "HH:mm"; // Formato da hora (24 horas)
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.getDefault());
        targetEditText.setText(sdf.format(calendar.getTime()));
    }

    /**
     * Lógica para confirmar a reserva.
     * Aqui você enviaria os dados para a API ou faria a persistência.
     */
    private void confirmarReserva() {
        // Exibir ProgressBar enquanto a operação está em andamento
        progressBarReserva.setVisibility(View.VISIBLE);
        btnConfirmarReserva.setEnabled(false); // Desabilita o botão para evitar múltiplos cliques

        // Obter os dados dos campos
        String professorNome = etProfessorNome.getText().toString().trim();
        String dataReserva = etDataReserva.getText().toString().trim();
        String horaInicio = etHoraInicio.getText().toString().trim();
        String horaFim = etHoraFim.getText().toString().trim();
        String justificativa = etJustificativa.getText().toString().trim();
        String salaSelecionada = tvSalaSelecionada.getText().toString().replace("Sala Selecionada: ", "").trim();


        // Validação básica dos campos
        if (professorNome.isEmpty() || dataReserva.isEmpty() || horaInicio.isEmpty() || horaFim.isEmpty() || justificativa.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
            progressBarReserva.setVisibility(View.GONE);
            btnConfirmarReserva.setEnabled(true);
            return;
        }

        // TODO: Implementar a chamada à API ou lógica de persistência para enviar a reserva
        // Exemplo de como você pode simular uma chamada assíncrona:
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // Ocultar ProgressBar e reabilitar botão após a operação (sucesso ou falha)
                        progressBarReserva.setVisibility(View.GONE);
                        btnConfirmarReserva.setEnabled(true);

                        // Aqui você verificaria o resultado da sua chamada à API
                        // Por exemplo:
                        boolean reservaBemSucedida = true; // Simular sucesso

                        if (reservaBemSucedida) {
                            Toast.makeText(ReservasActivity.this, "Reserva confirmada com sucesso!", Toast.LENGTH_LONG).show();
                            // Opcional: Navegar de volta para a tela de salas ou home
                            finish();
                        } else {
                            Toast.makeText(ReservasActivity.this, "Falha ao confirmar reserva. Tente novamente.", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                2000 // Simula um atraso de 2 segundos para a operação da rede/API
        );

        // Exemplo de como você poderia usar os dados (apenas para depuração)
        String dadosReserva = "Professor: " + professorNome +
                "\nData: " + dataReserva +
                "\nInício: " + horaInicio +
                "\nFim: " + horaFim +
                "\nJustificativa: " + justificativa +
                "\nSala: " + salaSelecionada;
        // Log.d("Reserva", dadosReserva); // Usaria um Log.d para depuração real
    }
}
