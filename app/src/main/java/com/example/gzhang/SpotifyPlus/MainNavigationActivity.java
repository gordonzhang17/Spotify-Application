package com.example.gzhang.SpotifyPlus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.gzhang.SpotifyPlus.Search.SearchActivity;

public class MainNavigationActivity extends MenuInflaterActivity {

    private Context mContext = MainNavigationActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupView();

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


}
