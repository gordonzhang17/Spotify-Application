package com.example.gzhang.SpotifyPlus.Search;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gzhang.SpotifyPlus.HelperMethods;
import com.example.gzhang.SpotifyPlus.R;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Track;

public class SearchSongViewAdapter extends RecyclerView.Adapter<SearchSongViewHolder> {

    private ArrayList<Track> mListOfTracks;
    private ArrayList<Bitmap> mListOfAlumbCovers;

    public SearchSongViewAdapter(ArrayList<Track> listOfTracks, ArrayList<Bitmap> listOfAlbumCovers) {
        mListOfTracks = listOfTracks;
        mListOfAlumbCovers = listOfAlbumCovers;
    }

    @Override
    public SearchSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_search_song, parent, false);

        return new SearchSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchSongViewHolder holder, int position) {
        String title = mListOfTracks.get(position).name;
        String artistsString = HelperMethods.getStringOfArtists(mListOfTracks.get(position));
        String album = mListOfTracks.get(position).album.name;


        holder.mTitle.setText(title);
        holder.mArtist.setText("Artist: " + artistsString);
        holder.mAlbum.setText("Album: " + album);
        holder.mAlbumCover.setImageBitmap(mListOfAlumbCovers.get(position));
    }

    @Override
    public int getItemCount() {
        return mListOfTracks.size();
    }

}
