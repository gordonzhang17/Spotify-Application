package com.example.gzhang.SpotifyPlus;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.protocol.types.Track;

import kaaes.spotify.webapi.android.models.AlbumSimple;

public class PlaySongActivity extends AppCompatActivity {

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
        getAlbumArt();
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

        titleTextView.setText(titleString);
        artistTextView.setText("Artist: " + artistsString);
        albumTextView.setText("Album: " + albumString);




    }

    private void getAlbumArt() {

        ImageView albumCoverImageView = (ImageView) findViewById(R.id.play_song_activity_song_album_cover);

        AlbumSimple album = mCurrentTrack.album;
        kaaes.spotify.webapi.android.models.Image image = album.images.get(0);
        String albumImageURL = image.url;
        //async task to get/set albumcoverart
        new RetrieveAlbumCoverTask(albumCoverImageView).execute(albumImageURL);


    }


    public void getLyrics(View view) {
        //TODO: get lyrics from Genius



    }
}
