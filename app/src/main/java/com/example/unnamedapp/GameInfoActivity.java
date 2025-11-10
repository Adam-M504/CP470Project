package com.example.unnamedapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GameInfoActivity extends AppCompatActivity {

    DatabaseHelper myhelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extrasreceived = getIntent().getExtras();
        if(extrasreceived != null){
            String GameId = extrasreceived.getString("Game_id");
            Log.i("GameInfoActivity", "Game Id received:" + GameId);
        }

        // Setup database
        myhelper = new DatabaseHelper(this);
        db = myhelper.getWritableDatabase();

        // Setup RecyclerViews for both teams
        RecyclerView recyclerTeam1 = findViewById(R.id.recyclerTeam1);
        RecyclerView recyclerTeam2 = findViewById(R.id.recyclerTeam2);

        recyclerTeam1.setLayoutManager(new LinearLayoutManager(this));
        recyclerTeam2.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data for testing (replace with DB query later)
        List<Player> team1Players = new ArrayList<>();
        team1Players.add(new Player("John Doe", "Team A", "Male"));
        team1Players.add(new Player("Chris Green", "Team A", "Male"));
        team1Players.add(new Player("Chris Green", "Team A", "Male"));
        team1Players.add(new Player("Chris Green", "Team A", "Male"));

        List<Player> team2Players = new ArrayList<>();
        team2Players.add(new Player("Jane Smith", "Team B", "Female"));
        team2Players.add(new Player("Sarah Johnson", "Team B", "Female"));
        team2Players.add(new Player("Chris Green", "Team A", "Male"));
        team2Players.add(new Player("Chris Green", "Team A", "Male"));

        // Set adapters
        PlayerAdapter adapterTeam1 = new PlayerAdapter(team1Players);
        PlayerAdapter adapterTeam2 = new PlayerAdapter(team2Players);

        recyclerTeam1.setAdapter(adapterTeam1);
        recyclerTeam2.setAdapter(adapterTeam2);
    }
}