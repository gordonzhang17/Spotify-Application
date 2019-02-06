package com.example.gzhang.SpotifyPlus.Search;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gzhang.SpotifyPlus.PlaySongActivity;
import com.example.gzhang.SpotifyPlus.R;

public class SearchSongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView mTitle;
    TextView mArtist;
    TextView mAlbum;
    ImageView mAlbumCover;

    SearchSongViewHolder(View view) {
        super(view);
        mTitle = view.findViewById(R.id.cell_search_song_title);
        mArtist = view.findViewById(R.id.cell_search_song_artist);
        mAlbum = view.findViewById(R.id.cell_search_song_album);
        mAlbumCover = view.findViewById(R.id.cell_search_album_cover);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int position = getAdapterPosition();

        //TODO: how to call method in search activity
        //searchActivity.songSelected(position);

    }

}
