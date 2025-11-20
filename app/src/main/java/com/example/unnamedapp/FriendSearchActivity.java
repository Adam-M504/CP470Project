package com.example.unnamedapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FriendSearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FriendAdapter adapter;
    private List<Friend> friendList, filteredList;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search);

        recyclerView = findViewById(R.id.recycler_view);
        searchBar = findViewById(R.id.search_bar);

        friendList = getFakeFriends();
        filteredList = new ArrayList<>(friendList);

        adapter = new FriendAdapter(filteredList, friend -> {
            Intent intent = new Intent(FriendSearchActivity.this, FriendPageActivity.class);
            intent.putExtra("name", friend.getName());
            intent.putExtra("username", friend.getUsername());
            intent.putExtra("status", friend.getStatus());
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterFriends(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private List<Friend> getFakeFriends() {
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Emily Carter", "emcarter", "Online"));
        friends.add(new Friend("Adam Li", "adamli", "Offline"));
        friends.add(new Friend("Zohib Patel", "zohibp", "In Game"));
        friends.add(new Friend("Michael D", "mdimaggio", "Online"));
        friends.add(new Friend("Harman Singh", "harman20", "Busy"));
        friends.add(new Friend("Jacqueline Scott", "jacqi_s", "Away"));
        return friends;
    }
    private void filterFriends(String query) {
        filteredList.clear();
        for (Friend f : friendList) {
            if (f.getName().toLowerCase().contains(query.toLowerCase()) ||
                    f.getUsername().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(f);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
