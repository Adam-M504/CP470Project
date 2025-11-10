// PlayerAdapter.java
package com.example.unnamedapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<Player> players;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, rank, gender;
        Button assignButton;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.playerName);
            rank = v.findViewById(R.id.playerRank);
            gender = v.findViewById(R.id.playerGender);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_player, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player p = players.get(position);
        holder.name.setText(p.name);
        holder.rank.setText("Rank: " + p.rank);
        holder.gender.setText("Gender: " + p.gender);

    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
