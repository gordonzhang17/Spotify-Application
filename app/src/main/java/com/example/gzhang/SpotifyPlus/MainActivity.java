package com.example.gzhang.SpotifyPlus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends MenuInflaterActivity {

    private Context mContext = MainActivity.this;

    private String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        onStart();

    }
    public void continueToNextScreen(View view) {

        Intent intent = new Intent(mContext, LoginAndConnectActivity.class);
        startActivity(intent);
    }

    public void sendUserToDownloadSpotify(View view) {
        HelperMethods.sendToDownloadSpotifyApp(mContext);
    }
}
