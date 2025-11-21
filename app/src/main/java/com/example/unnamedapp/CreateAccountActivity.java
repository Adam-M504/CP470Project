package com.example.unnamedapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateAccountActivity extends AppCompatActivity {


    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);

        createAccountButton = findViewById(R.id.create_account_button);

        createAccountButton.setOnClickListener(v -> {
                    Intent intent = new Intent(CreateAccountActivity.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }
        );
    }
}