package com.example.unnamedapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FriendPageActivity extends AppCompatActivity {

    private Button sendFriendRequestBtn;
    private Button backButton;
    private boolean friendRequestSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_page);

        // Initialize views
        ImageView rankBadge = findViewById(R.id.friend_rank_badge);
        TextView nameView = findViewById(R.id.friend_page_name);
        TextView usernameView = findViewById(R.id.friend_page_username);
        TextView rankView = findViewById(R.id.friend_page_rank);
        TextView statusView = findViewById(R.id.friend_page_status);
        TextView gamesPlayedView = findViewById(R.id.friend_games_played);
        TextView winsView = findViewById(R.id.friend_wins);
        TextView lossesView = findViewById(R.id.friend_losses);
        sendFriendRequestBtn = findViewById(R.id.send_friend_request_btn);
        backButton = findViewById(R.id.back_button);

        // Get data from intent
        String name = getIntent().getStringExtra("name");
        String username = getIntent().getStringExtra("username");
        String rank = getIntent().getStringExtra("rank");
        String status = getIntent().getStringExtra("status");

        // Set text values
        nameView.setText(name != null ? name : "Player Name");
        usernameView.setText(username != null ? username : "@player");
        statusView.setText(status != null ? status : "Ready to play!");

        // Set rank badge and rank name based on rank
        setRankBadge(rankBadge, rankView, rank);

        // Placeholder stats (will be loaded from database later)
        gamesPlayedView.setText("0");
        winsView.setText("0");
        lossesView.setText("0");

        // Back button click handler
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this activity and return to previous screen
            }
        });

        // Friend request button click handler
        final String playerName = name != null ? name : "this player";
        sendFriendRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleFriendRequest(playerName);
            }
        });
    }

    private void handleFriendRequest(String playerName) {
        if (!friendRequestSent) {
            // TODO: Add database implementation to actually send friend request
            friendRequestSent = true;
            sendFriendRequestBtn.setText("Friend Request Sent");
            sendFriendRequestBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#999999")));
            sendFriendRequestBtn.setEnabled(false);

            Toast.makeText(this, "Friend request sent to " + playerName + "!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void setRankBadge(ImageView badge, TextView rankText, String rank) {
        if (rank == null) {
            rank = "Barefoot"; // Default rank
        }

        switch (rank.toLowerCase()) {
            case "grand ball master":
                badge.setImageResource(R.drawable.rank_grand_ball_master);
                rankText.setText("Grand Ball Master");
                rankText.setTextColor(Color.parseColor("#E63946"));
                break;
            case "footy fanatic":
                badge.setImageResource(R.drawable.rank_footy_fanatic);
                rankText.setText("Footy Fanatic");
                rankText.setTextColor(Color.parseColor("#009961"));
                break;
            case "ball master":
                badge.setImageResource(R.drawable.rank_ball_master);
                rankText.setText("Ball Master");
                rankText.setTextColor(Color.parseColor("#FF6B35"));
                break;
            case "kinda mid":
                badge.setImageResource(R.drawable.rank_kinda_mid);
                rankText.setText("Kinda Mid");
                rankText.setTextColor(Color.parseColor("#666666"));
                break;
            case "dirty sneaker":
                badge.setImageResource(R.drawable.rank_dirty_sneaker);
                rankText.setText("Dirty Sneaker");
                rankText.setTextColor(Color.parseColor("#E67E22"));
                break;
            case "barefoot":
            default:
                // Using built-in icon as placeholder until you add the images
                badge.setImageResource(android.R.drawable.ic_menu_gallery);
                rankText.setText("Barefoot");
                rankText.setTextColor(Color.parseColor("#E67E22"));
                break;
        }
    }
}
