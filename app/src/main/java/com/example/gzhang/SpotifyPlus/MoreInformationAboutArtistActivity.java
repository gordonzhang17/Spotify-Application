package com.example.gzhang.SpotifyPlus;

import android.content.Context;
import android.content.Intent;
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
        setContentView(R.layout.activity_search_artist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = MoreInformationAboutArtistActivity.this;

        setupSearchBarListener();

    }


    private void setupSearchBarListener() {

        mSearchBar = (EditText) findViewById(R.id.search_artist_info_activity_search_view);

        mSearchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String userInputText = mSearchBar.getText().toString();
                    String URL = createURLString(userInputText);
                    showWebView(URL);
                    return true;
                }
                return false;
            }
        });
    }

    private String createURLString(String userInput) {

        //TODO: do this until you figure out how to use genius api
        //TODO: separate by dashes
        //String[] userInputArray = userInput.split(" ");

//        String newInput = "";
//
//        for (int i = 0; i < userInputArray.length; i++) {
//            newInput = newInput + "-" + userInputArray[i];
//        }

        String geniusWebsiteURL = "https://genius.com/artists/";
        String url = geniusWebsiteURL + userInput;

        return url;

    }

    private void showWebView(String URL) {

        Intent intent = new Intent(mContext, ArtistWebViewActivity.class);
        intent.putExtra("URL", URL);
        startActivity(intent);

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
