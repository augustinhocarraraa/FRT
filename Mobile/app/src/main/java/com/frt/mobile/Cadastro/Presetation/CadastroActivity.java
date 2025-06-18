package com.frt.mobile.Cadastro.Presetation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton; // Importe ImageButton
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.frt.mobile.R;
import com.frt.mobile.Cadastro.Data.Cadastro;
import com.frt.mobile.Cadastro.Utils.CadastroService;
import com.frt.mobile.Login.Presetation.LoginActivity;
import com.frt.mobile.Shared.Utils.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText; // IMPORTANTE: Certifique-se deste import

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText etNome, etEmail, etSenha, etConfirmarSenha;
    private Spinner spinnerPerfil;
    private Button btnCadastrar;
    private TextView tvVoltarLogin;
    private ProgressBar progressBarCadastro;
    private ImageButton btnBackArrowCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Associa os elementos da UI
        etNome = findViewById(R.id.et_cadastro_nome);
        etEmail = findViewById(R.id.et_cadastro_email);
        etSenha = findViewById(R.id.et_cadastro_senha);
        etConfirmarSenha = findViewById(R.id.et_cadastro_confirmar_senha);
        spinnerPerfil = findViewById(R.id.spinner_perfil);
        btnCadastrar = findViewById(R.id.btn_cadastrar);
        tvVoltarLogin = findViewById(R.id.tv_voltar_login);
        progressBarCadastro = findViewById(R.id.progress_bar_cadastro);
        btnBackArrowCadastro = findViewById(R.id.btn_back_arrow_cadastro);

        // Configura o listener de clique para o botão de voltar personalizado
        btnBackArrowCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finaliza a Activity ao clicar na seta
            }
        });

        // Configura o ArrayAdapter para o Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.perfis_usuario,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPerfil.setAdapter(adapter);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCadastro();
            }
        });

        tvVoltarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void performCadastro() {
        // As chamadas .getText() aqui são para objetos TextInputEditText, que possuem esse método.
        String nome = etNome.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();
        String confirmarSenha = etConfirmarSenha.getText().toString().trim();
        String perfilSelecionado = spinnerPerfil.getSelectedItem().toString();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBarCadastro.setVisibility(View.VISIBLE);
        btnCadastrar.setEnabled(false);

        Cadastro cadastroRequest = new Cadastro(nome, email, senha, perfilSelecionado);

        CadastroService cadastroService = RetrofitClient.getRetrofitInstance().create(CadastroService.class);
        cadastroService.cadastrarUsuario(cadastroRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressBarCadastro.setVisibility(View.GONE);
                btnCadastrar.setEnabled(true);

                if (response.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso! Faça login.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    String errorMessage = "Falha no cadastro. Tente novamente.";
                    if (response.errorBody() != null) {
                        try {
                            // TODO: Parsear errorBody para uma mensagem de erro mais específica da API
                        } catch (Exception e) {
                            Log.e("Cadastro", "Erro ao parsear errorBody: " + e.getMessage());
                        }
                    }
                    Toast.makeText(CadastroActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressBarCadastro.setVisibility(View.GONE);
                btnCadastrar.setEnabled(true);
                Toast.makeText(CadastroActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Cadastro", "Erro na chamada da API: " + t.getMessage(), t);
            }
        });
    }
}
