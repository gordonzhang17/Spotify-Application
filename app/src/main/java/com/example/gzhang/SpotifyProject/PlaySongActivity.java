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
import com.spotify.protocol.types.Artist;
import com.spotify.protocol.types.Track;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistSimple;

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


    private void setupTrackInfo(kaaes.spotify.webapi.android.models.Track currentTrack) {


        TextView titleTextView = (TextView) findViewById(R.id.play_song_activity_song_title);
        TextView artistTextView = (TextView) findViewById(R.id.play_song_activity_song_artist);
        TextView albumTextView = (TextView) findViewById(R.id.play_song_activity_song_album);

        String titleString = currentTrack.name;

        String artistsString = "";


        for (ArtistSimple artist : currentTrack.artists) {
            artistsString.concat(artistsString + " " + artist.name);
        }

        String albumString = currentTrack.album.name;

        titleTextView.setText("Title: " + titleString);
        artistTextView.setText("Artist: " + artistsString);
        albumTextView.setText("Artist: " + albumString);

    }

    private void setupTrackInfo(Track currentTrack) {

        TextView titleTextView = (TextView) findViewById(R.id.play_song_activity_song_title);
        TextView artistTextView = (TextView) findViewById(R.id.play_song_activity_song_artist);
        TextView albumTextView = (TextView) findViewById(R.id.play_song_activity_song_album);

        String titleString = currentTrack.name;

        String artistsString = "";

        for (int i = 0 ; i < currentTrack.artists.size(); i++) {
            String artistName = currentTrack.artists.get(i).name;
            artistsString = artistName + " " + artistsString;

        }

        String albumString = currentTrack.album.name;

        titleTextView.setText("Title: " + titleString);
        artistTextView.setText("Artist: " + artistsString);
        albumTextView.setText("Artist: " + albumString);

    }

    private void findLyrics() {


        //TODO: search for lyircs either from Spotify or other service


    }

    private void playSong() {

        HelperMethods.play("spotify:track:4Km5HrUvYTaSUfiSGPJeQR");
    }

    private void subscribeToPlayerState() {

        mPlayerApi
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        setupTrackInfo(track);
                    } else {
                        Log.d("MainNavigationActivity", "track doesn't work!");

                    }
                });
    }

}
