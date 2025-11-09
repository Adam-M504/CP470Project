package com.example.unnamedapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unnamedapp.DateAdapter;
import com.example.unnamedapp.Game;
import com.example.unnamedapp.GameAdapter;


import java.util.*;

public class HomePage extends AppCompatActivity {

    RecyclerView dateRecyclerView, gameRecyclerView;
    DateAdapter dateAdapter;
    GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        dateRecyclerView = findViewById(R.id.dateRecyclerView);
        gameRecyclerView = findViewById(R.id.gameRecyclerView);

        // horizontal date list
        List<String> dates = Arrays.asList("8", "9", "10", "11", "12");
        dateAdapter = new DateAdapter(dates, selectedDate -> {
            Toast.makeText(this, "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
            // TODO: query SQL Table_Game to update game list for this date
        });
        dateRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dateRecyclerView.setAdapter(dateAdapter);

        // game list
        List<Game> games = new ArrayList<>();
        games.add(new Game("Game1", "3PM", "Field 1"));
        games.add(new Game("Game2", "5PM", "Field 2"));
        games.add(new Game("Game3", "6PM", "Field 3"));

        gameAdapter = new GameAdapter(games);
        gameRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gameRecyclerView.setAdapter(gameAdapter);
    }
}
