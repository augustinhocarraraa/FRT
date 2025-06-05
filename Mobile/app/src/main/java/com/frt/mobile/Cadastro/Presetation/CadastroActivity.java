package com.frt.mobile.Cadastro.Presetation;

import static com.frt.mobile.R.id.editNome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.frt.mobile.Login.Presetation.LoginActivity;
import com.frt.mobile.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editEmail;
    private EditText editSenha;
    private Button btnCadastrar;
    private TextView textLink;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro); // Assuming your XML is named activity_cadastro.xml

        // Initialize views
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        textLink = findViewById(R.id.textLink);

        // Set click listener for register button
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Set click listener for login text (to navigate to login activity)
        textLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
    }

    private void registerUser() {
        String name = editNome.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editSenha.getText().toString().trim();

        // Basic validation
        if (name.isEmpty()) {
            editNome.setError("Nome é obrigatório");
            editNome.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editEmail.setError("Email é obrigatório");
            editEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editSenha.setError("Senha é obrigatória");
            editSenha.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editSenha.setError("Senha deve ter pelo menos 6 caracteres");
            editSenha.requestFocus();
            return;
        }

        // Here you would typically call your registration API/service
        // For now, we'll just show a success message
        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

        // After successful registration, you might want to navigate to another activity
        // navigateToMainActivity();
    }

    private void navigateToLogin() {
        // Replace LoginActivity.class with your actual login activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Optional: close this activity
    }

    // Optional method to navigate after successful registration
    /*
    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    */
}