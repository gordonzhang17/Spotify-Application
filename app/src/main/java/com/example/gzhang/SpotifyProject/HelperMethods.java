package com.example.gzhang.SpotifyProject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;

public class HelperMethods {

    public static com.spotify.android.appremote.api.SpotifyAppRemote getSpotifyAppRemote() {
        SpotifyAppRemoteSingleton spotifyAppRemoteSingleton = SpotifyAppRemoteSingleton.getInstance();
        return spotifyAppRemoteSingleton.getSpotifyAppRemote();
    }

    public static void setSpotifyAppRemote(com.spotify.android.appremote.api.SpotifyAppRemote spotifyAppRemote) {
        SpotifyAppRemoteSingleton spotifyAppSingleton = SpotifyAppRemoteSingleton.getInstance();
        spotifyAppSingleton.setSpotifyAppRemote(spotifyAppRemote);
    }

    public static void play(String uri) {
        SpotifyAppRemote spotifyAppRemote = getSpotifyAppRemote();
        spotifyAppRemote.getPlayerApi().play(uri);

    }

    public static PlayerApi getPlayerApi() {
        SpotifyAppRemote spotifyAppRemote = getSpotifyAppRemote();
        return spotifyAppRemote.getPlayerApi();
    }

    public static void sendToDownloadSpotifyApp(Context context) {

        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.spotify.music&hl=en" + appPackageName)));
        } catch (android.content.ActivityNotFoundException activityNotFoundException) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=acom.spotify.music&hl=en" + appPackageName)));
        }
    }

    public String getDisplayName() {
        CurrentUserSingleton currentUserSingleton = CurrentUserSingleton.getInstance();
        return currentUserSingleton.getCurrentUser().getDisplayName();
    }

    public String getAccessToken() {
        CurrentUserSingleton currentUserSingleton = CurrentUserSingleton.getInstance();
        return currentUserSingleton.getCurrentUser().getAccessToken();
    }

    public String getEmail() {
        CurrentUserSingleton currentUserSingleton = CurrentUserSingleton.getInstance();
        return currentUserSingleton.getCurrentUser().getEmail();
    }


}


