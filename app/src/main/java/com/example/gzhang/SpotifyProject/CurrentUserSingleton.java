package com.example.gzhang.SpotifyProject;

import com.spotify.android.appremote.api.SpotifyAppRemote;

public class CurrentUserSingleton {

    private static com.spotify.android.appremote.api.SpotifyAppRemote mSpotifyAppRemote;

    public void setSpotifyRemote(com.spotify.android.appremote.api.SpotifyAppRemote spotifyRemote) {

        if (mSpotifyAppRemote == null) {
            mSpotifyAppRemote = spotifyRemote;
        }
    }

    public com.spotify.android.appremote.api.SpotifyAppRemote getSpotifyAppRemote() {

        return mSpotifyAppRemote;
    }

}
