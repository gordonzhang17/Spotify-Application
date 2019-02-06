package com.example.gzhang.SpotifyPlus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class RetrieveAlbumCoverTask extends AsyncTask<String, Void, Bitmap> {

    //private ImageView mImageView;

    public RetrieveAlbumCoverTask() {
        //this.mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap albumArtBitmap = null;

        try {
            InputStream inputStream = new java.net.URL(url) .openStream();
            albumArtBitmap = BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {
            Log.i("download image error:", e.getMessage());
        }

        return albumArtBitmap;
    }

    protected void onPostExecute(Bitmap result) {
        //TODO: call interface

        //mImageView.setImageBitmap(result);
    }

}
