package com.example.unnamedapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unnamedapp.HomePage;
import com.example.unnamedapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText loginBox, passwordBox;
    private Button loginButton, createAccountButton, forgotPwButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // Ensure this matches your XML filename

        // Hook up views
        loginBox            = findViewById(R.id.login_box);
        passwordBox         = findViewById(R.id.password_box);
        loginButton         = findViewById(R.id.login_button);
        createAccountButton = findViewById(R.id.create_account_button);
        forgotPwButton      = findViewById(R.id.forgot_pw_button);

        // --- LOGIN BUTTON LOGIC ---
        loginButton.setOnClickListener(v -> {

            String username = loginBox.getText().toString().trim();
            String password = passwordBox.getText().toString().trim();

            // Check for empty fields
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,
                        getString(R.string.incorrect),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // (Later you can add real username/password verification here)

            // If both fields filled -> go to HomePage
            Intent intent = new Intent(LoginActivity.this, HomePage.class);
            startActivity(intent);
            finish();
        });

        // --- Other buttons (placeholders for now) ---
        createAccountButton.setOnClickListener(v -> {
                    Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                    startActivity(intent);
                }
        );

        forgotPwButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        }
        );
    }
}