package com.example.gzhang.SpotifyProject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;

public class HelperMethods {

    public static com.spotify.android.appremote.api.SpotifyAppRemote getSpotifyAppRemote() {
        CurrentUserSingleton spotifyRemote = new CurrentUserSingleton();
        return spotifyRemote.getSpotifyAppRemote();
    }

    public static void setSpotifyAppRemote(com.spotify.android.appremote.api.SpotifyAppRemote spotifyAppRemote) {
        CurrentUserSingleton spotifyAppSingleton = new CurrentUserSingleton();
        spotifyAppSingleton.setSpotifyRemote(spotifyAppRemote);
    }

    public static void play(String uri) {
        SpotifyAppRemote spotifyAppRemote = HelperMethods.getSpotifyAppRemote();
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


}


