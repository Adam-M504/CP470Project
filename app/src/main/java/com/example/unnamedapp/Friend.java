package com.example.unnamedapp;

public class Friend {
    private String name;
    private String username;
    private String status;

    public Friend(String name, String username, String status) {
        this.name = name;
        this.username = username;
        this.status = status;
    }

    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getStatus() { return status; }
}
