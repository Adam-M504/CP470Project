package com.example.unnamedapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    private List<Friend> friends;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Friend friend);
    }
    public FriendAdapter(List<Friend> friends, OnItemClickListener listener) {
        this.friends = friends;
        this.listener = listener;
    }
    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_search, parent, false);
        return new FriendViewHolder(v);
    }
    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        holder.bind(friends.get(position), listener);
    }
    @Override
    public int getItemCount() {
        return friends.size();
    }
    static class FriendViewHolder extends RecyclerView.ViewHolder {
        TextView name, username, status;
        FriendViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.friend_name);
            username = itemView.findViewById(R.id.friend_username);
            status = itemView.findViewById(R.id.friend_status);
        }
        void bind(final Friend friend, final OnItemClickListener listener) {
            name.setText(friend.getName());
            username.setText(friend.getUsername());
            status.setText(friend.getStatus());
            itemView.setOnClickListener(v -> listener.onItemClick(friend));
        }
    }
}
