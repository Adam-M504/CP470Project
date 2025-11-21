// GameHistoryAdapter.java
package com.example.unnamedapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.ViewHolder> {

    private List<Game> games;

    public GameHistoryAdapter(List<Game> games) {
        this.games = games;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, time, location, date;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.historyGameName);
            time = v.findViewById(R.id.historyGameTime);
            location = v.findViewById(R.id.historyGameLocation);
            date = v.findViewById(R.id.historyGameDate);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_game, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game g = games.get(position);
        holder.name.setText(g.name);
        holder.time.setText("Time: " + g.time);
        holder.location.setText("Location: " + g.location);
        holder.date.setText("Date: " + g.date);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}
