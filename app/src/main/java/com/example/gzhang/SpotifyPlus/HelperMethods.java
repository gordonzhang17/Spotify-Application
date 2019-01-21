package com.example.gzhang.SpotifyPlus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Artist;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.ArtistSimple;
import kaaes.spotify.webapi.android.models.Track;

public class HelperMethods {

    public static com.spotify.android.appremote.api.SpotifyAppRemote getSpotifyAppRemote() {
        SpotifyAppRemoteSingleton spotifyAppRemoteSingleton = SpotifyAppRemoteSingleton.getInstance();
        return spotifyAppRemoteSingleton.getSpotifyAppRemote();
    }

    public static void setSpotifyAppRemote(com.spotify.android.appremote.api.SpotifyAppRemote spotifyAppRemote) {
        SpotifyAppRemoteSingleton spotifyAppSingleton = SpotifyAppRemoteSingleton.getInstance();
        spotifyAppSingleton.setSpotifyAppRemote(spotifyAppRemote);
    }

    public static void play(String uri) {
        SpotifyAppRemote spotifyAppRemote = getSpotifyAppRemote();
        spotifyAppRemote.getPlayerApi().play(uri);

    }

    public static PlayerApi getPlayerApi() {
        SpotifyAppRemote spotifyAppRemote = getSpotifyAppRemote();
        return spotifyAppRemote.getPlayerApi();
    }

    public static void sendToDownloadSpotifyApp(Context context) {

        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.spotify.music&hl=en" + appPackageName)));
        } catch (android.content.ActivityNotFoundException activityNotFoundException) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=acom.spotify.music&hl=en" + appPackageName)));
        }
    }

    public static String getDisplayName() {
        CurrentUserSingleton currentUserSingleton = CurrentUserSingleton.getInstance();
        return currentUserSingleton.getCurrentUser().getDisplayName();
    }

    public static String getEmail() {
        CurrentUserSingleton currentUserSingleton = CurrentUserSingleton.getInstance();
        return currentUserSingleton.getCurrentUser().getEmail();
    }

    public static String getStringOfArtists(Track track) {

        List<ArtistSimple> listOfArtists = track.artists;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < listOfArtists.size(); i++) {

            if (i == listOfArtists.size() - 1) {
                // last artist
                stringBuilder.append(listOfArtists.get(i).name);

            } else {
                //other artists
                stringBuilder.append(listOfArtists.get(i).name).append(", ");

            }
        }
        return stringBuilder.toString().trim();
    }

    public static String getStringOfArtists(com.spotify.protocol.types.Track track) {

        List<Artist> listOfArtists = track.artists;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < listOfArtists.size(); i++) {

            if (i == listOfArtists.size() - 1) {
                // last artist
                stringBuilder.append(listOfArtists.get(i).name);

            } else {
                //other artists
                stringBuilder.append(listOfArtists.get(i).name).append(", ");

            }
        }
        return stringBuilder.toString().trim();
    }

            //TODO: create helper to return accessToken

//    public static String getAccessToken(AppCompatActivity appCompatActivity, Context context) {
//
//
//        int REQUEST_CODE = 1337;
//        String REDIRECT_URI = "http://localhost:8888/callback";
//        String CLIENT_ID = "8a2929a563264e3a900f55ac6b90a334";
//
//        AuthenticationRequest.Builder builder =
//                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
//
//        builder.setScopes(new String[]
//
//                {
//                        "streaming"
//                });
//
//        AuthenticationRequest request = builder.build();
//
//        AuthenticationClient.openLoginActivity(appCompatActivity, REQUEST_CODE, request);
//
//
//
//
//        protected void onActivityResult (int requestCode, int resultCode, Intent intent){
//            super.onActivityResult(requestCode, resultCode, intent);
//
//            // Check if result comes from the correct activity
//            if (requestCode == REQUEST_CODE) {
//                AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
//
//                switch (response.getType()) {
//                    // Response was successful and contains auth token
//                    case TOKEN:
//                        // Handle successful response
//                        return response.getAccessToken();
//
//                    break;
//
//                    // Auth flow returned an error
//                    case ERROR:
//                        // Handle error response
//                        authenticationErrorPopup(context);
//
//                        //TODO: create popup if theres is no internet
//
////                    if (response.getCode() == "-2") {
////                        //no internet
////                    }
//                        break;
//
//                    // Most likely auth flow was cancelled
//                    default:
//                        // Handle other cases
////                    Intent returnToMainActivityIntent = new Intent(mContext, MainActivity.class);
////                    startActivity(returnToMainActivityIntent);
//
//                        //return to MainActivity class
//                        return null;
//                }
//            }
//        }
//
//
//    }

            private static void authenticationErrorPopup (Context context){

                final AlertDialog.Builder builder;

                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);

                final AlertDialog alert = builder.create();

                builder.setTitle(context.getResources().getString(R.string.login_activity_login_authentication_error_title));
                builder.setMessage(context.getResources().getString(R.string.login_activity_login_authentication_description));
                builder.setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alert.cancel();

                    }
                });
                builder.setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.cancel();
                    }
                });
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.show();
            }


        }


