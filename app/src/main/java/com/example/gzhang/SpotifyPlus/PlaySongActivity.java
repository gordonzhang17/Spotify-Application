package com.example.gzhang.SpotifyPlus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.protocol.types.Track;

import kaaes.spotify.webapi.android.models.AlbumSimple;

public class PlaySongActivity extends AppCompatActivity {

    private PlayerApi mPlayerApi;
    private kaaes.spotify.webapi.android.models.Track mCurrentTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setup title and artist
        //mPlayerApi = HelperMethods.getPlayerApi();
        mCurrentTrack = getIntent().getParcelableExtra("TRACK");
        setupTrackInfo();
        HelperMethods.play(mCurrentTrack.uri);
        //subscribeToPlayerState();

    }

    private void setupTrackInfo() {


        TextView titleTextView = (TextView) findViewById(R.id.play_song_activity_song_title);
        TextView artistTextView = (TextView) findViewById(R.id.play_song_activity_song_artist);
        TextView albumTextView = (TextView) findViewById(R.id.play_song_activity_song_album);

        String titleString = mCurrentTrack.name;

        String artistsString = HelperMethods.getStringOfArtists(mCurrentTrack);

        String albumString = mCurrentTrack.album.name;

        titleTextView.setText("Title: " + titleString);
        artistTextView.setText("Artist: " + artistsString);
        albumTextView.setText("Artist: " + albumString);


    }

    private void getAlbumArt() {
        //TODO: get album image

        AlbumSimple album = mCurrentTrack.album;
        kaaes.spotify.webapi.android.models.Image image = album.images.get(0);
        String albumImageURL = image.url;


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
        albumTextView.setText("Album: " + albumString);

    }

    private void findLyrics() {


        //TODO: search for lyircs either from Spotify or other service


    }

    private void subscribeToPlayerState() {

        mPlayerApi
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        setupTrackInfo();
                    } else {
                        Log.d("PlaySongActivity: ", "track doesn't work!");

                    }
                });
    }

}
