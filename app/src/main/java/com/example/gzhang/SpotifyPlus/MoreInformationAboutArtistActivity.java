package com.example.gzhang.SpotifyPlus;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class MoreInformationAboutArtistActivity extends AppCompatActivity {

    private EditText mSearchBar;
    private Context mContext;

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information_about_artist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSearchBar = (EditText) findViewById(R.id.search_artist_info_activity_search_view);
        mContext = MoreInformationAboutArtistActivity.this;

        showWebView("");


        //setupSearchBarListener();

    }

    private void setupSearchBarListener() {

        mSearchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String userInputText = mSearchBar.getText().toString();
                String URL = createURLString(userInputText);
                showWebView(URL);
                return true;
            }
            return false;
        });
    }

    private String createURLString(String userInput) {

        //TODO: decide what website you want the user to look at

        return "";


    }

    private void showWebView(String URL) {

        //TODO: shouldn't you go to the new activity to show the webview?

        mWebView = new WebView(mContext);
        setContentView(mWebView);
        mWebView.loadUrl("https://www.genius.com");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


}
