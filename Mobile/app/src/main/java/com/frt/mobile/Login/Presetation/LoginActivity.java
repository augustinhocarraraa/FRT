// Mobile/app/src/main/java/com/frt/mobile/Login/Presetation/LoginActivity.java
package com.frt.mobile.Login.Presetation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.frt.mobile.Home.Presetation.HomeActivity; // Para redirecionar após o login
import com.frt.mobile.Login.Data.Login;
import com.frt.mobile.Login.Data.LoginResponse;
import com.frt.mobile.Login.Utils.AuthService;
import com.frt.mobile.R; // Para acessar os IDs do layout
import com.frt.mobile.Shared.Utils.RetrofitClient; // O cliente Retrofit que você já tem

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton; // Para o botão de cadastro (se existir)

    // Chave para armazenar o token nas SharedPreferences
    private static final String PREF_NAME = "MyAppPrefs";
    private static final String TOKEN_KEY = "auth_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Certifique-se de que este layout existe e tem os IDs corretos

        emailEditText = findViewById(R.id.edit_text_email); // ID do campo de email no seu layout
        passwordEditText = findViewById(R.id.edit_text_password); // ID do campo de senha no seu layout
        loginButton = findViewById(R.id.button_login); // ID do botão de login no seu layout
        registerButton = findViewById(R.id.button_register); // ID do botão de cadastro no seu layout (se existir)


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        // Opcional: Listener para o botão de cadastro
        if (registerButton != null) {
            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crie um Intent para a sua Activity de Cadastro
                    // Exemplo: startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
                    Toast.makeText(LoginActivity.this, "Navegar para tela de Cadastro", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void performLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criar o objeto de requisição de login
        Login loginRequest = new Login(email, password);

        // Obter a instância do serviço de autenticação via Retrofit
        AuthService authService = RetrofitClient.getRetrofitInstance().create(AuthService.class);

        // Fazer a chamada assíncrona para a API
        Call<LoginResponse> call = authService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    String token = loginResponse.getToken();
                    // String tipoToken = loginResponse.getTipo(); // Se precisar do tipo (ex: "Bearer")

                    // Salvar o token no SharedPreferences
                    saveAuthToken(token);

                    Toast.makeText(LoginActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

                    // Redirecionar para a HomeActivity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza a LoginActivity para que o usuário não possa voltar
                } else {
                    // Se a resposta não for bem-sucedida (ex: 401 Unauthorized, 400 Bad Request)
                    String errorMessage = "Erro no login.";
                    if (response.errorBody() != null) {
                        try {
                            // Tenta ler a mensagem de erro do corpo da resposta de erro
                            errorMessage = response.errorBody().string();
                            // Você pode querer parsear este errorMessage para um DTO de erro específico se sua API o fornecer
                            Log.e("LoginActivity", "Erro na API: " + errorMessage);
                        } catch (Exception e) {
                            Log.e("LoginActivity", "Erro ao ler errorBody: " + e.getMessage());
                        }
                    }
                    Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                // Erro de rede ou outra falha
                Log.e("LoginActivity", "Erro de conexão: " + t.getMessage(), t);
                Toast.makeText(LoginActivity.this, "Erro de conexão. Verifique sua internet.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveAuthToken(String token) {
        SharedPreferences sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply(); // apply() salva assincronamente, commit() salva sincronicamente
        Log.d("LoginActivity", "Token salvo: " + token);
    }

    // Opcional: Método para verificar se o token existe (útil na MainActivity para auto-login)
    public static String getAuthToken(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(TOKEN_KEY, null); // Retorna null se não houver token
    }

    // Opcional: Método para remover o token (para logout)
    public static void clearAuthToken(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
        Log.d("LoginActivity", "Token removido.");
    }
}