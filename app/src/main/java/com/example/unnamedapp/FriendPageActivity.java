package com.example.unnamedapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FriendPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_page);

        TextView nameView = findViewById(R.id.friend_page_name);
        TextView usernameView = findViewById(R.id.friend_page_username);
        TextView statusView = findViewById(R.id.friend_page_status);

        String name = getIntent().getStringExtra("name");
        String username = getIntent().getStringExtra("username");
        String status = getIntent().getStringExtra("status");

        nameView.setText(name);
        usernameView.setText(username);
        statusView.setText(status);
    }
}
