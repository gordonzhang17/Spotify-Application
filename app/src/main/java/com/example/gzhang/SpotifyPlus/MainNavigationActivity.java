package com.example.gzhang.SpotifyPlus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Helper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gzhang.SpotifyPlus.Search.SearchActivity;
import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.protocol.types.Track;

public class MainNavigationActivity extends MenuInflaterActivity {

    private Context mContext = MainNavigationActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupView();
        subscribeToPlayerState();

    }

    private void setupView() {

        TextView userNameTextView = (TextView) findViewById(R.id.main_navigation_user_name_title);
        CurrentUserSingleton currentUserSingleton = CurrentUserSingleton.getInstance();
        String username = currentUserSingleton.getCurrentUser().getDisplayName();
        String title = "Welcome: " + username;
        userNameTextView.setText(title);

    }



    public void returnToMainActivity(View view) {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }

    public void playSong(View view) {
        Intent intent = new Intent(mContext, PlaySongActivity.class);
        startActivity(intent);
    }

    public void searchForASong(View view) {
        Intent intent = new Intent(mContext, SearchActivity.class);
        startActivity(intent);
    }

    private void subscribeToPlayerState() {
        PlayerApi playerApi = HelperMethods.getPlayerApi();

        playerApi
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        setupTrackInfo(track);
                    } else {
                        Log.d("MainNavigationActivity:", "track doesn't work!");

                    }
                });
    }

    private void setupTrackInfo(Track track) {


        TextView titleTextView = (TextView) findViewById(R.id.main_navigation_song_title);
        TextView artistTextView = (TextView) findViewById(R.id.main_navigation_song_artist);
        TextView albumTextView = (TextView) findViewById(R.id.main_navigation_song_album);

        String titleString = track.name;

        String artistsString = HelperMethods.getStringOfArtists(track);

        String albumString = track.album.name;

        titleTextView.setText(titleString);
        artistTextView.setText("Artist: " + artistsString);
        albumTextView.setText("Album: " + albumString);


        }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }

}
