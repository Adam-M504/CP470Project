package com.example.unnamedapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateAccountActivity extends AppCompatActivity {


    private Button createAccountButton;
    private EditText password;
    private EditText confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);

        createAccountButton = findViewById(R.id.create_account_button);
        password = findViewById(R.id.password_input);
        confirm_password = findViewById(R.id.confirm_password_input);


        createAccountButton.setOnClickListener(v -> {


                String pass_string = password.getText().toString().trim();
                String pass_confirm_string = confirm_password.getText().toString().trim();

//                confirm that password matches password confirmation
                    if (pass_string.equals(pass_confirm_string)){

                        Intent intent = new Intent(CreateAccountActivity.this, HomePage.class);
                        startActivity(intent);
                        finish();
                }
                }
        );
    }
}