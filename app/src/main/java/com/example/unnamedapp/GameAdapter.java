// GameAdapter.java
package com.example.unnamedapp;

import android.content.Intent;
import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private List<Game> games;

    public GameAdapter(List<Game> games) {
        this.games = games;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, time, location;
        Button signUp;
        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.gameName);
            time = v.findViewById(R.id.gameTime);
            location = v.findViewById(R.id.gameLocation);

            signUp = v.findViewById(R.id.btnSignUp);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game g = games.get(position);
        holder.name.setText(g.name);
        holder.time.setText("Time: " + g.time);
        holder.location.setText("Location: " + g.location);

        holder.signUp.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Signed up for " + g.name, Toast.LENGTH_SHORT).show();
            HomePage.CallGameInfo(g.name, v.getContext());
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}
