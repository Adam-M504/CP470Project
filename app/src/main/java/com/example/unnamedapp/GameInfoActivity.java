package com.example.unnamedapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GameInfoActivity extends AppCompatActivity {

    DatabaseHelper myhelper;
    SQLiteDatabase db;
    String GameId;

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
            GameId = extrasreceived.getString("Game_id");
            Log.i("GameInfoActivity", "Game Id received:" + GameId);

        }





        // Setup database
        myhelper = new DatabaseHelper(this);
        db = myhelper.getWritableDatabase();

        List<String> team_ids = GetTeamId(GameId);

        String team1id = team_ids.get(0);
        String team2id = team_ids.get(1);

        databaseentrycreator(db);

        // Setup RecyclerViews for both teams
        RecyclerView recyclerTeam1 = findViewById(R.id.recyclerTeam1);
        RecyclerView recyclerTeam2 = findViewById(R.id.recyclerTeam2);

        recyclerTeam1.setLayoutManager(new LinearLayoutManager(this));
        recyclerTeam2.setLayoutManager(new LinearLayoutManager(this));


        Log.i("GameInfoActivity",   "TEAM 1 ID: " + team1id + " TEAM 2 ID: " + team2id);

        // Dummy data for testing (replace with DB query later)
        List<Player> team1Players = CreatePlayerList(team1id);


        List<Player> team2Players = CreatePlayerList(team2id);

        // Set adapters
        PlayerAdapter adapterTeam1 = new PlayerAdapter(team1Players);
        PlayerAdapter adapterTeam2 = new PlayerAdapter(team2Players);

        recyclerTeam1.setAdapter(adapterTeam1);
        recyclerTeam2.setAdapter(adapterTeam2);

        SetDisplay(GameId);
        db.close();

    }


    public void SetDisplay(String game_id){
        TextView DateDisplay = findViewById(R.id.tvDate);
        TextView LocationDisplay = findViewById(R.id.tvLocation);
        TextView TimeDisplay = findViewById(R.id.tvTime);


        Cursor gamecursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME_GAME + " WHERE " +
                DatabaseHelper.KEY_G_ID + " = " + game_id , null);

        if (gamecursor.moveToFirst()){
            DateDisplay.setText(gamecursor.getString(gamecursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_DATE)));
            LocationDisplay.setText(gamecursor.getString(gamecursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_LOCATION)));
            TimeDisplay.setText(gamecursor.getString(gamecursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_TIME)));
        }
        gamecursor.close();
        return;
    }


    public List<String> GetTeamId(String game_id){
        List<String> teamids = new ArrayList<>();

        Cursor gamecursor = db.rawQuery("SELECT " + DatabaseHelper.KEY_G_T1 + " , " +
                DatabaseHelper.KEY_G_T2 + " FROM " + DatabaseHelper.TABLE_NAME_GAME + " WHERE " +
                DatabaseHelper.KEY_G_ID + " = " + game_id , null);

        if (gamecursor.moveToFirst()){
            teamids.add(gamecursor.getString(gamecursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_T1)));
            teamids.add(gamecursor.getString(gamecursor.getColumnIndexOrThrow(DatabaseHelper.KEY_G_T2)));
        }
        gamecursor.close();
        return teamids;
    }

    public List<Player> CreatePlayerList(String teamid){
        List<Player> teamplayers = new ArrayList<>();

        Cursor gc = db.rawQuery("SELECT " + DatabaseHelper.KEY_T_P1 + ", " +
                DatabaseHelper.KEY_T_P2 + ", " + DatabaseHelper.KEY_T_GOALIE + " FROM " +
                DatabaseHelper.TABLE_NAME_TEAM + " WHERE " + DatabaseHelper.KEY_T_ID + " = " + teamid, null);

        if(gc.moveToFirst()){


            String p1_id = gc.getString(gc.getColumnIndexOrThrow(DatabaseHelper.KEY_T_P1));
            String p2_id = gc.getString(gc.getColumnIndexOrThrow(DatabaseHelper.KEY_T_P2));
            String goalie_id = gc.getString(gc.getColumnIndexOrThrow(DatabaseHelper.KEY_T_GOALIE));

            Cursor pc = db.rawQuery(
                    "SELECT * FROM " + DatabaseHelper.TABLE_NAME_PLAYER +
                            " WHERE " + DatabaseHelper.KEY_P_ID + " IN (?, ?, ?)",
                    new String[]{ goalie_id, p1_id, p2_id}
            );

            while (pc.moveToNext()){
                String name = pc.getString(pc.getColumnIndexOrThrow(DatabaseHelper.KEY_P_NAME));
                String rank = pc.getString(pc.getColumnIndexOrThrow(DatabaseHelper.KEY_P_RANK));
                String gender = pc.getString(pc.getColumnIndexOrThrow(DatabaseHelper.KEY_P_GEND));

                teamplayers.add(new Player(name, rank, gender));
            }
            pc.close();


        }

        //String get_team_values = "SELECT " +DatabaseHelper.KEY_G_T1

        gc.close();
        return teamplayers;



    }

    public static void databaseentrycreator(SQLiteDatabase dbase){
        Log.i("GameInfoActivity", "entering team entries");
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.KEY_P_ID, "1");
        values.put(DatabaseHelper.KEY_P_GEND, "M");
        values.put(DatabaseHelper.KEY_P_NAME, "John Soccer");
        values.put(DatabaseHelper.KEY_P_RANK, "Ball Master");
        dbase.insert(DatabaseHelper.TABLE_NAME_PLAYER, null, values);

        values = new ContentValues();

        values.put(DatabaseHelper.KEY_P_ID, "2");
        values.put(DatabaseHelper.KEY_P_GEND, "M");
        values.put(DatabaseHelper.KEY_P_NAME, "Ronaldo");
        values.put(DatabaseHelper.KEY_P_RANK, "Grand Ball Master");
        dbase.insert(DatabaseHelper.TABLE_NAME_PLAYER, null, values);

        values = new ContentValues();

        values.put(DatabaseHelper.KEY_P_ID, "3");
        values.put(DatabaseHelper.KEY_P_GEND, "M");
        values.put(DatabaseHelper.KEY_P_NAME, "Messi");
        values.put(DatabaseHelper.KEY_P_RANK, "Grand Ball Master");
        dbase.insert(DatabaseHelper.TABLE_NAME_PLAYER, null, values);

        values = new ContentValues();

        values.put(DatabaseHelper.KEY_T_ID, "1");
        values.put(DatabaseHelper.KEY_T_P1, "1");
        values.put(DatabaseHelper.KEY_T_P2, "2");
        values.put(DatabaseHelper.KEY_T_GOALIE, "3");
        dbase.insert(DatabaseHelper.TABLE_NAME_TEAM, null, values);

        values = new ContentValues();

        values.put(DatabaseHelper.KEY_P_ID, "4");
        values.put(DatabaseHelper.KEY_P_GEND, "M");
        values.put(DatabaseHelper.KEY_P_NAME, "John Soccer");
        values.put(DatabaseHelper.KEY_P_RANK, "Ball Master");
        dbase.insert(DatabaseHelper.TABLE_NAME_PLAYER, null, values);

        values = new ContentValues();

        values.put(DatabaseHelper.KEY_P_ID, "5");
        values.put(DatabaseHelper.KEY_P_GEND, "M");
        values.put(DatabaseHelper.KEY_P_NAME, "Ronaldo");
        values.put(DatabaseHelper.KEY_P_RANK, "Grand Ball Master");
        dbase.insert(DatabaseHelper.TABLE_NAME_PLAYER, null, values);

        values = new ContentValues();

        values.put(DatabaseHelper.KEY_P_ID, "6");
        values.put(DatabaseHelper.KEY_P_GEND, "M");
        values.put(DatabaseHelper.KEY_P_NAME, "Messi");
        values.put(DatabaseHelper.KEY_P_RANK, "Grand Ball Master");
        dbase.insert(DatabaseHelper.TABLE_NAME_PLAYER, null, values);

        values = new ContentValues();

        values.put(DatabaseHelper.KEY_T_ID, "2");
        values.put(DatabaseHelper.KEY_T_P1, "4");
        values.put(DatabaseHelper.KEY_T_P2, "5");
        values.put(DatabaseHelper.KEY_T_GOALIE, "6");
        dbase.insert(DatabaseHelper.TABLE_NAME_TEAM, null, values);

    }
}