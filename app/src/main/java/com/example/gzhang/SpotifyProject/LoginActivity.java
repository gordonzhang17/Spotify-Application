package com.example.gzhang.SpotifyProject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class LoginActivity extends Activity {

    //Does authentication

    // Request code will be used to verify if result comes from the login activity. Can be set to any integer.
    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
    private static final String CLIENT_ID = "8a2929a563264e3a900f55ac6b90a334";

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = LoginActivity.this;

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

                    Intent newIntent = new Intent(mContext, PlayPlaylistActivity.class);
                    startActivity(newIntent);

                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    String hello = "";

                    //TODO: create a popup saying that you don't have a logged in spotify app on your device
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
                    String sdf = "";
            }
        }
    }
}
