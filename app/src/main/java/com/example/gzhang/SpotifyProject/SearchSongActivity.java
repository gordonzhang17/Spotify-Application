package com.example.gzhang.SpotifyProject;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

public class SearchSongActivity extends AppCompatActivity {

    private EditText mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_song);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupSearchBarListener();
    }

    private void setupSearchBarListener() {

        mSearchView = (EditText) findViewById(R.id.search_song_activity_search_view);

        //TODO: debug this. pressing enter only moves down a line and the findSong() method isnt called

        mSearchView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    findSong();
                    return true;
                }
                return false;
            }
        });
    }

//    edittext.setOnKeyListener(new OnKeyListener() {
//        public boolean onKey(View v, int keyCode, KeyEvent event) {
//            // If the event is a key-down event on the "enter" button
//            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                // Perform action on key press
//                Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            return false;
//        }

    private void findSong() {

        //TODO: find song based on user input


        String userInputText = mSearchView.getText().toString();

        SpotifyApi spotifyApi = new SpotifyApi();

        CurrentUserSingleton currentUserSingleton = CurrentUserSingleton.getInstance();
        String accessToken = currentUserSingleton.getCurrentUser().getAccessToken();
        spotifyApi.setAccessToken(accessToken);

        SpotifyService spotifyService = spotifyApi.getService();

        kaaes.spotify.webapi.android.models.Track currentTrack = spotifyService.getTrack(userInputText);

        String songURI = currentTrack.uri;

        HelperMethods.play(songURI);

    }


}
