package com.example.gzhang.SpotifyProject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.RemoteClient;
import com.spotify.protocol.client.error.RemoteClientException;
import com.spotify.protocol.types.Track;
import com.spotify.protocol.types.Types;

public class MainNavigationActivity extends MenuInflaterActivity {

    private Context mContext = MainNavigationActivity.this;
    private PlayerApi mPlayerApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupView();
        mPlayerApi = HelperMethods.getPlayerApi();

    }

    private void setupView() {

        TextView userNameTextView = (TextView) findViewById(R.id.main_navigation_user_name_title);
        //TODO: how to get user's name
        String username = "Daniel";
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

    public void playPlaylist() {
        //request id = 3 means it plays successfully

    mPlayerApi.play("spotify:track:4B0JvthVoAAuygILe3n4Bs");

    }


}
