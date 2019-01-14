package com.example.gzhang.SpotifyProject;

import android.os.Bundle;
import android.support.constraint.solver.widgets.Helper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

public class PlaySongActivity extends AppCompatActivity {

    private SpotifyAppRemote mSpotifyAppRemote;
    private PlayerApi mPlayerApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setup title and artist
        mSpotifyAppRemote = HelperMethods.getSpotifyAppRemote();
        mPlayerApi = HelperMethods.getPlayerApi();
        playSong();
        subscribeToPlayerState();
    }

    private void findSong() {

        //TODO: find song based on user input

        EditText userInputEditText = (EditText) findViewById(R.id.play_song_activity_search_edit_text);

        String userInputText = userInputEditText.getText().toString();

        SpotifyApi spotifyApi = new SpotifyApi();

        //spotifyApi.setAccessToken();

        SpotifyService spotifyService = spotifyApi.getService();

        spotifyService.getTrack(userInputText);

        //TODO: if successful and it is playing, search for lyrics
    }

    private void findLyrics() {


        //TODO: search for lyircs either from Spotify or other service


    }

    private void playSong() {

        HelperMethods.play("spotify:track:50kpGaPAhYJ3sGmk6vplg0");
    }

    private void subscribeToPlayerState() {

        mPlayerApi
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        setupTitleAndArtist(track);
                    } else {
                        Log.d("MainNavigationActivity", "track doesn't work!");

                    }
                });
    }

    private void setupTitleAndArtist(Track track) {

        TextView titleTextView = (TextView) findViewById(R.id.play_song_activity_song_title);
        TextView artistTextView = (TextView) findViewById(R.id.play_song_activity_song_artist);

        String title = track.name;
        String artist = track.artist.name;

        titleTextView.setText("Title: " + title);
        artistTextView.setText("Artist: " + artist);

    }
}
