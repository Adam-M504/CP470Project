package com.example.unnamedapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryPage extends AppCompatActivity {

    RecyclerView historyRecyclerView;
    GameHistoryAdapter historyAdapter;

    DatabaseHelper myhelper;
    SQLiteDatabase db;
    ImageButton Home_button;
    ImageButton Friends_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        Home_button = findViewById(R.id.navHome);
        Friends_button = findViewById(R.id.navFriends);

        Home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HistoryPage","Navigating to home page");
                Intent intent = new Intent(HistoryPage.this, HomePage.class);
                startActivity(intent);
            }
        });
        Friends_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HistoryPage", "Navigating to friends page");
                Intent intent = new Intent(HistoryPage.this, FriendSearchActivity.class);
                startActivity(intent);
            }
        });


        historyRecyclerView = findViewById(R.id.recyclerHistory);

        myhelper = new DatabaseHelper(this);
        db = myhelper.getReadableDatabase();

        loadHistoryGames();
    }

    private void loadHistoryGames() {
        List<Game> games = new ArrayList<>();

        // For now, fetch ALL games. Later you'll filter with playerId.
        String query = "SELECT "
                + DatabaseHelper.KEY_G_ID + ", "
                + DatabaseHelper.KEY_G_TIME + ", "
                + DatabaseHelper.KEY_G_LOCATION + ", "
                + DatabaseHelper.KEY_G_DATE
                + " FROM " + DatabaseHelper.TABLE_NAME_GAME;

        Cursor cursor = db.rawQuery(query, null);

        Log.i("HistoryPage", "Query: " + query);
        Log.i("HistoryPage", "Row count: " + cursor.getCount());

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                String gameId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_ID));
                String gameTime = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_TIME));
                String gameLocation = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_LOCATION));
                String gameDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_DATE));

                Log.i("HistoryPage", "Loaded Game: " + gameId + " " + gameDate);

                games.add(new Game(gameId, gameTime, gameLocation, gameDate));

                cursor.moveToNext();
            }
        }

        cursor.close();

        // Attach adapter
        historyAdapter = new GameHistoryAdapter(games);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerView.setAdapter(historyAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
