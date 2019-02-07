package com.example.gzhang.SpotifyPlus.Search;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gzhang.SpotifyPlus.PlaySongActivity;
import com.example.gzhang.SpotifyPlus.R;
import com.example.gzhang.SpotifyPlus.RetrieveAlbumCoverInterface;
import com.example.gzhang.SpotifyPlus.RetrieveAlbumCoverTask;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchActivity extends AppCompatActivity {

    private EditText mSearchView;
    private Context mContext;

    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
    private static final String CLIENT_ID = "8a2929a563264e3a900f55ac6b90a334";

    private RecyclerView mRecyclerView;
    private SearchSongViewAdapter mSearchAdapter;

    private ArrayList<Track> mListOfTracks = new ArrayList<>();
    private ArrayList<Bitmap> mListOfAlbumCovers = new ArrayList<>();

    private RetrieveAlbumCoverInterface mRetrieveAlbumCoverInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_song);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupSearchBarListener();
        mContext = SearchActivity.this;

        mRecyclerView = findViewById(R.id.search_song_activity_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mSearchAdapter = new SearchSongViewAdapter(mListOfTracks, mListOfAlbumCovers, songSelectedInterface);

//        mRetrieveAlbumCoverInterface = new RetrieveAlbumCoverInterface() {
//            @Override
//            public void onAlbumCoverImageRetrieved(Bitmap image) {
//                mListOfAlbumCovers.add(image);
//            }
//        };

    }


    private void setupSearchBarListener() {

        mSearchView = (EditText) findViewById(R.id.search_song_activity_search_view);

        mSearchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    newSearch();
                    beginAuthentication();
                    return true;
                }
                return false;
            }
        });
    }

    private void beginAuthentication() {

        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]

                {
                        "streaming"
                });

        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    String accessToken = response.getAccessToken();
                    findSong(accessToken);

                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response

                    //TODO: create popup if theres is no internet

//                    if (response.getCode() == "-2") {
//                        //no internet
//                    }
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
//                    Intent returnToMainActivityIntent = new Intent(mContext, MainActivity.class);
//                    startActivity(returnToMainActivityIntent);

                    //return to MainActivity class
                    finish();
            }
        }
    }

    private void findSong(String accessToken) {

        String userInputText = mSearchView.getText().toString();
        SpotifyApi spotifyApi = new SpotifyApi();
        spotifyApi.setAccessToken(accessToken);
        SpotifyService spotifyService = spotifyApi.getService();

        spotifyService.searchTracks(userInputText, new Callback<TracksPager>() {
            @Override
            public void success(TracksPager tracksPager, Response response) {
                //TODO: this is where you should make the recyclerview with this list of tracks

                List<Track> listOfTracks = tracksPager.tracks.items;

                List<String> albumCoverURLs = getAlbumCoverURLs(listOfTracks);

                fetchAlbumCovers(albumCoverURLs);

                mListOfTracks.addAll(listOfTracks);
                mRecyclerView.setAdapter(mSearchAdapter);

            }

            @Override
            public void failure(RetrofitError error) {

                final AlertDialog.Builder builder;

                builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material);

                final AlertDialog alert = builder.create();

                builder.setTitle("Search Error");
                builder.setMessage("Couldn't find the given song title. Please try again");
                builder.setPositiveButton(getResources().getString(R.string.alert_dialog_close), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alert.cancel();

                    }
                });
                builder.show();

            }
        });
    }

    private void newSearch() {

        mListOfTracks.clear();
        mListOfAlbumCovers.clear();
        mSearchAdapter.notifyDataSetChanged();

    }

    private List<String> getAlbumCoverURLs(List<Track> listOfTracks) {

        List<String> albumCoverURls = new ArrayList<>();

        for (int i = 0; i < listOfTracks.size(); i++) {
            String albumCoverURL = listOfTracks.get(i).album.images.get(0).url;
            albumCoverURls.add(albumCoverURL);
        }

        return albumCoverURls;

    }

    private SongSelectedInterface songSelectedInterface = new SongSelectedInterface() {
        @Override
        public void songSelected(int positionOfSongSelected) {

            Intent intent = new Intent(mContext, PlaySongActivity.class);
            intent.putExtra("TRACK", mListOfTracks.get(positionOfSongSelected));
            startActivity(intent);

        }
    };

    private void fetchAlbumCovers(List<String> albumCoverURLs) {
        //TODO:this does not work, maybe revert back to old code


        for (int i = 0; i < albumCoverURLs.size() ; i++) {
            //RetrieveAlbumCoverTask retrieveAlbumCoverTask = new RetrieveAlbumCoverTask(mRetrieveAlbumCoverInterface);
            String albumImageURL = albumCoverURLs.get(i);
            //retrieveAlbumCoverTask.execute(albumImageURL);
            try {
                URL url = new URL(albumImageURL);
                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                mListOfAlbumCovers.add(image);

            } catch (Exception e) {
                Log.i("download image error:", e.getMessage());
            }
        }
    }
}
