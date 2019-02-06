package com.example.gzhang.SpotifyPlus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spotify.android.appremote.api.SpotifyAppRemote;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Track;

public class MenuInflaterActivity extends AppCompatActivity {

    private Context mContext = MenuInflaterActivity.this;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showAppDescription(MenuItem item) {

        final AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Dialog_Alert);

        final AlertDialog alert = builder.create();

        builder.setTitle(getResources().getString(R.string.app_description_title));
        builder.setMessage(getResources().getString(R.string.app_description_description));
        builder.setPositiveButton(getResources().getString(R.string.alert_dialog_close), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                alert.cancel();

            }
        });
        builder.show();
    }

    public void closeApp(MenuItem item) {

        SpotifyAppRemote spotifyAppRemote = HelperMethods.getSpotifyAppRemote();

        SpotifyAppRemote.disconnect(spotifyAppRemote);

        finishAffinity();
    }
}
