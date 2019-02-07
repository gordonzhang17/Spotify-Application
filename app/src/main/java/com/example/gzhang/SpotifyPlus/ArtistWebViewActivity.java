package com.example.gzhang.SpotifyPlus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kaaes.spotify.webapi.android.models.ArtistSimple;

public class ArtistWebViewActivity extends AppCompatActivity {

    private Context mContext;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = ArtistWebViewActivity.this;
        mWebView = findViewById(R.id.webview);

        String URL = getIntent().getStringExtra("URL");
        setupWebview(URL);
    }

    private void setupWebview(String URL) {

        mWebView = new WebView(mContext);
        setContentView(mWebView);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(URL);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(mContext, MainNavigationActivity.class);
        startActivity(intent);
    }

}
