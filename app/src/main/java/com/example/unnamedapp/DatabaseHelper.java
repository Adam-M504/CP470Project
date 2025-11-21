package com.example.unnamedapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Soccer.db";
    private static final int VERSION_NUM = 6;


    public static final String TABLE_NAME_PLAYER = "Table_Player";
    public static final String KEY_P_ID = "id";
    public static final String KEY_P_NAME = "name";
    public static final String KEY_P_FIRSTNAME = "firstname";
    public static final String KEY_P_LASTNAME = "lastname";
    private static final String KEY_P_PASSWORD = "password";
    private static final String KEY_P_AGE = "age";
    private static final String KEY_P_SKILL_LEVEL = "skill level";
    public static final String KEY_P_RANK = "rank";
    public static final String KEY_P_GEND = "gender";


    public static final String TABLE_NAME_FRIENDS = "Friend_Table";
    public static final String KEY_F_ID = "fid";
    public static final String Key_F_ID_FRIEND_1 = "friend_1";
    public static final String Key_F_ID_FRIEND_2 = "friend_2";

    public static final String KEY_F_REQUEST_ACCEPTED = "friend_request";


    public static final String TABLE_NAME_TEAM = "Table_Team";
    public static final String KEY_T_ID = "id";
    public static final String KEY_T_GOALIE = "goalie";
    public static final String KEY_T_P1 = "p1";
    public static final String KEY_T_P2 = "p2";

    public static final String TABLE_NAME_GAME = "Table_Game";
    public static final String KEY_G_ID = "id";
    public static final String KEY_G_DATE = "date";
    public static final String KEY_G_LOCATION = "location";
    public static final String KEY_G_TIME = "time";
    public static final String KEY_G_T1 = "team_1_id";
    public static final String KEY_G_T2 = "team_2_id";
    public static final String KEY_G_T1_SCORE = "team_1_score";
    public static final String KEY_G_T2_SCORE = "team_2_score";
    public static final String KEY_G_REF_ID = "ref_id";

    public DatabaseHelper(Context ctx){
            super(ctx, DATABASE_NAME, null, VERSION_NUM);
        }

        public void onCreate(SQLiteDatabase db){
            Log.i("DatabaseHelper", "Calling onCreate");
            String create_player_table = "CREATE TABLE " + TABLE_NAME_PLAYER +" ("+KEY_P_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_P_NAME +
                    " TEXT, "+KEY_P_RANK+" TEXT, "+KEY_P_GEND+" TEXT, "+KEY_P_FIRSTNAME+ " TEXT, " +KEY_P_LASTNAME+ " TEXT, " + KEY_P_AGE+ " TEXT, " +
                     KEY_P_SKILL_LEVEL + " TEXT, "+KEY_P_PASSWORD+ " TEXT) ";
            db.execSQL(create_player_table);
            Log.i("DatabaseHelper", "Player Table Created");

            String create_team_table = "CREATE TABLE " + TABLE_NAME_TEAM +" ("+KEY_T_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_T_GOALIE +
                    " TEXT, "+KEY_T_P1+" TEXT, "+KEY_T_P2+" TEXT)";
            db.execSQL(create_team_table);
            Log.i("DatabaseHelper", "Team Table Created");

            String create_game_table = "CREATE TABLE " + TABLE_NAME_GAME +" ("+KEY_G_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_G_DATE +
                    " TEXT, "+KEY_G_LOCATION+" TEXT, "+KEY_G_TIME+" TEXT, "+KEY_G_T1+" TEXT, "+KEY_G_T2+" TEXT, "+KEY_G_T1_SCORE+" TEXT, "+
                    KEY_G_T2_SCORE+" TEXT, "+KEY_G_REF_ID+" TEXT)";
            db.execSQL(create_game_table);

            String create_friend_table = "CREATE TABLE " + TABLE_NAME_FRIENDS + " ("+KEY_F_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    Key_F_ID_FRIEND_1 + " TEXT, " + Key_F_ID_FRIEND_2 + " TEXT, " + KEY_F_REQUEST_ACCEPTED + " BOOLEAN)";
            db.execSQL(create_friend_table);

            Log.i("DatabaseHelper", "Game Table Created");


        }

        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
            Log.i("DatabaseHelper", "Calling onUpgrade, oldVersion="+oldVer+" newVersion="+ newVer);
            String replace_table_team = "DROP TABLE IF EXISTS "+ TABLE_NAME_TEAM;
            db.execSQL(replace_table_team);
            String replace_table_player = "DROP TABLE IF EXISTS "+ TABLE_NAME_PLAYER;
            db.execSQL(replace_table_player);
            String replace_table_game = "DROP TABLE IF EXISTS "+ TABLE_NAME_GAME;
            db.execSQL(replace_table_game);
            onCreate(db);
        }


}
