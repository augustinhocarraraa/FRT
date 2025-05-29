package com.frt.mobile.cadastro.presetation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.frt.mobile.R;
import com.frt.mobile.login.presetation.LoginActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText userEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro); // Assuming your XML is named activity_cadastro.xml

        // Initialize views
        userEditText = findViewById(R.id.nome_field);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.loginButton);
        loginTextView = findViewById(R.id.textView3);

        // Set click listener for register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Set click listener for login text (to navigate to login activity)
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
    }

    private void registerUser() {
        String name = userEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Basic validation
        if (name.isEmpty()) {
            userEditText.setError("Nome é obrigatório");
            userEditText.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailEditText.setError("Email é obrigatório");
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Senha é obrigatória");
            passwordEditText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Senha deve ter pelo menos 6 caracteres");
            passwordEditText.requestFocus();
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