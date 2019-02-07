package com.example.gzhang.SpotifyPlus.Search;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gzhang.SpotifyPlus.HelperMethods;
import com.example.gzhang.SpotifyPlus.PlaySongActivity;
import com.example.gzhang.SpotifyPlus.R;

import kaaes.spotify.webapi.android.models.Track;

public class SearchSongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTitle;
    private TextView mArtist;
    private TextView mAlbum;
    private ImageView mAlbumCover;

    private SongSelectedInterface mSongSelectedInterface;

    SearchSongViewHolder(View view, SongSelectedInterface songSelectedInterface) {
        super(view);

        mTitle = view.findViewById(R.id.cell_search_song_title);
        mArtist = view.findViewById(R.id.cell_search_song_artist);
        mAlbum = view.findViewById(R.id.cell_search_song_album);
        mAlbumCover = view.findViewById(R.id.cell_search_album_cover);
        mSongSelectedInterface = songSelectedInterface;
        itemView.setOnClickListener(this);
    }

    public void bindView(Track track, Bitmap image) {

        String title = track.name;
        String artistsString = HelperMethods.getStringOfArtists(track);
        String album = track.album.name;

        mTitle.setText(title);
        mArtist.setText("Artist: " + artistsString);
        mAlbum.setText("Album: " + album);
        mAlbumCover.setImageBitmap(image);
    }


    @Override
    public void onClick(View v) {

        int position = getAdapterPosition();
        mSongSelectedInterface.songSelected(position);

    }

}
