package com.example.gzhang.SpotifyProject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainNavigationActivity extends AppCompatActivity {

    private Context mContext = MainNavigationActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void returnToMainActivity(View view) {
        Intent intent = new Intent(mContext ,MainActivity.class);
        startActivity(intent);
    }
}
