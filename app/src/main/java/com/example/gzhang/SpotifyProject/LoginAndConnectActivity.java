package com.example.gzhang.SpotifyProject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.UserPrivate;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginAndConnectActivity extends Activity {

    //Does authentication and connect to Spotify App

    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
    private static final String CLIENT_ID = "8a2929a563264e3a900f55ac6b90a334";

    private Context mContext;

    private String accessToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = LoginAndConnectActivity.this;

        if (HelperMethods.getSpotifyAppRemote() == null || !HelperMethods.getSpotifyAppRemote().isConnected()) {
            beginAuthentication();
        } else {
            Intent intent = new Intent(mContext, MainNavigationActivity.class);
            startActivity(intent);
        }

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
                    accessToken = response.getAccessToken();
                    retrieveUserInfo();

                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    authenticationErrorPopup();

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

    private void retrieveUserInfo() {

        SpotifyApi spotifyApi = new SpotifyApi();

        spotifyApi.setAccessToken(accessToken);

        SpotifyService spotifyService = spotifyApi.getService();

        spotifyService.getMe(new Callback<UserPrivate>() {
            @Override
            public void success(UserPrivate userPrivate, Response response) {
                String email = userPrivate.email;
                String name = userPrivate.display_name;

                CurrentUser currentUser = new CurrentUser(name, email, accessToken);

                CurrentUserSingleton currentUserSingleton = CurrentUserSingleton.getInstance();
                currentUserSingleton.setCurrentUser(currentUser);

                connectToSpotifyApp();

            }

            @Override
            public void failure(RetrofitError error) {

                //TODO: error handling

            }
        });



    }

    private void authenticationErrorPopup() {

        final AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Dialog_Alert);

        final AlertDialog alert = builder.create();

        builder.setTitle(getResources().getString(R.string.login_activity_login_authentication_error_title));
        builder.setMessage(getResources().getString(R.string.login_activity_login_authentication_description));
        builder.setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                alert.cancel();

            }
        });
        builder.setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                beginAuthentication();
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    private void connectToSpotifyApp() {

        ConnectionParams connectionParams =

                new ConnectionParams.Builder(CLIENT_ID)
                        .showAuthView(true)
                        .setRedirectUri(REDIRECT_URI)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        HelperMethods.setSpotifyAppRemote(spotifyAppRemote);
                        connected();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        connectionErrorPopup();


                    }
                });
    }

    private void connected() {
        Intent intent = new Intent(mContext, MainNavigationActivity.class);
        startActivity(intent);


    }

    private void connectionErrorPopup() {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Dialog_Alert);


        builder.setTitle(getResources().getString(R.string.login_activity_connection_error_title));
        builder.setMessage(getResources().getString(R.string.login_activity_connection_error_description));
        builder.setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                HelperMethods.sendToDownloadSpotifyApp(mContext);

            }
        });

        builder.setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                connectToSpotifyApp();
            }
        });

        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();

    }


}
