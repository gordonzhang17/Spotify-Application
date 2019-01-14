package com.example.gzhang.SpotifyProject;

public class CurrentUser {

    private String displayName;
    private String accessToken;
    private String email;


    public CurrentUser(String displayName, String accessToken, String email) {
        this.displayName = displayName;
        this.accessToken = accessToken;
        this.email = email;
    }


    public String getDisplayName() {
        return displayName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getEmail() {
        return email;
    }
}
