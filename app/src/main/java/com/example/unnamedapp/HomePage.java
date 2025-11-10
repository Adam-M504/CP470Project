package com.example.unnamedapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unnamedapp.DateAdapter;
import com.example.unnamedapp.Game;
import com.example.unnamedapp.GameAdapter;


import java.time.LocalDate;
import java.util.*;

public class HomePage extends AppCompatActivity {

    RecyclerView dateRecyclerView, gameRecyclerView;
    DateAdapter dateAdapter;

    GameAdapter gameAdapter;
    DatabaseHelper myhelper;
    SQLiteDatabase db;
    ImageButton Trophy_button;

    LocalDate todays_date = LocalDate.now();

    String selected_date = todays_date.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        dateRecyclerView = findViewById(R.id.dateRecyclerView);
        gameRecyclerView = findViewById(R.id.gameRecyclerView);

        // horizontal date list
//        todays_date = LocalDate.now(); // todays date object created
        List<String> dates = Arrays.asList(todays_date.toString(), (todays_date.plusDays(1)).toString(), (todays_date.plusDays(2)).toString(), (todays_date.plusDays(3)).toString(),
                (todays_date.plusDays(4)).toString(), (todays_date.plusDays(5)).toString(), (todays_date.plusDays(6)).toString(), (todays_date.plusDays(7)).toString());
        dateAdapter = new DateAdapter(dates, selectedDate -> {
            Toast.makeText(this, "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
            // change selected date to selectedDate, refresh the page
            selected_date = selectedDate;
            Log.i("HomePage", "New date selected: "+selected_date);
            // start home page
            CreateGameList();
            //Intent intent = new Intent(HomePage.this, HomePage.class);
            //startActivity(intent);
        });
        dateRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dateRecyclerView.setAdapter(dateAdapter);

        Trophy_button = findViewById(R.id.navStats);

        myhelper = new DatabaseHelper(this);
        db = myhelper.getWritableDatabase();



        Trophy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HomePage", "New date selected: "+selected_date);
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.KEY_G_DATE, selected_date);
                values.put(DatabaseHelper.KEY_G_LOCATION, "Field 1");
                values.put(DatabaseHelper.KEY_G_TIME, "6PM");
                values.put(DatabaseHelper.KEY_G_T1, "1");
                values.put(DatabaseHelper.KEY_G_T2, "2");
                values.put(DatabaseHelper.KEY_G_T1_SCORE, "0");
                values.put(DatabaseHelper.KEY_G_T2_SCORE, "0");
                values.put(DatabaseHelper.KEY_G_REF_ID, "1");
                db.insert(DatabaseHelper.TABLE_NAME_GAME, null, values);
                Log.i("HomePage","Database Updated, Game Added");
            }
        });

        CreateGameList();

    }

    public void CreateGameList(){
        // game list
        List<Game> games = new ArrayList<>();

        String get_game_values = "SELECT "+DatabaseHelper.KEY_G_ID +", "+DatabaseHelper.KEY_G_TIME+", "
                +DatabaseHelper.KEY_G_LOCATION+", "+DatabaseHelper.KEY_G_DATE+" FROM " + DatabaseHelper.TABLE_NAME_GAME
                + " WHERE "+ DatabaseHelper.KEY_G_DATE + " = " + "'"+selected_date+"'";

        Cursor cursor = db.rawQuery(get_game_values, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Log.i("ChatWindow", "Cursor's column count ="+cursor.getColumnCount());
            String games_id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_ID));
            String games_time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_TIME));
            String games_location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_LOCATION));
            String games_date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_DATE));
            games.add(new Game(games_id, games_time,games_location, games_date));

            cursor.moveToNext();
        }

        cursor.close();


//        games.add(new Game("Game1", "3PM", "Field 1"));
//        games.add(new Game("Game2", "5PM", "Field 2"));
//        games.add(new Game("Game3", "6PM", "Field 3"));

        gameAdapter = new GameAdapter(games);
        gameRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gameRecyclerView.setAdapter(gameAdapter);
    }

    public static void CallGameInfo(String selected_game_id, Context ctx){
        Intent intent = new Intent(ctx, GameInfoActivity.class);
        intent.putExtra("Game_id", selected_game_id);
        ctx.startActivity(intent);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("HomePage","onDestroy, Closing Database");
        db.close();
    }
}
