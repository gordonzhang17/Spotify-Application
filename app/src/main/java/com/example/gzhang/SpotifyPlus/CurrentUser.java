package com.example.gzhang.SpotifyPlus;

public class CurrentUser {

    private String displayName;
    private String email;


    public CurrentUser(String displayName, String email) {
        this.displayName = displayName;
        this.email = email;
    }


    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}
