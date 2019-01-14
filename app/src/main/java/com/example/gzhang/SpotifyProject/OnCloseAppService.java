package com.example.gzhang.SpotifyProject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.spotify.android.appremote.api.SpotifyAppRemote;

public class OnCloseAppService extends Service {

    SpotifyAppRemote mSpotifyAppRemote = HelperMethods.getSpotifyAppRemote();


    //TODO: find out how to determine if app is closed here
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        //unregister listeners
        //do any other cleanup if required

        SpotifyAppRemote.disconnect(mSpotifyAppRemote);

        //stop service
        stopSelf();
    }
}
