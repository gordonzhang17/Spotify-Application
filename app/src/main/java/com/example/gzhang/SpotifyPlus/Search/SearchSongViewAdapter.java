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
    private ArrayList<Bitmap> mListOfAlbumCovers;
    private SongSelectedInterface mSongSelectedInterface;

    public SearchSongViewAdapter(ArrayList<Track> listOfTracks, ArrayList<Bitmap> listOfAlbumCovers, SongSelectedInterface songSelectedInterface) {
        mListOfTracks = listOfTracks;
        mListOfAlbumCovers = listOfAlbumCovers;
        mSongSelectedInterface = songSelectedInterface;
    }

    @Override
    public SearchSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_search_song, parent, false);

        return new SearchSongViewHolder(view, mSongSelectedInterface);
    }

    @Override
    public void onBindViewHolder(SearchSongViewHolder holder, int position) {

        holder.bindView(mListOfTracks.get(position), mListOfAlbumCovers.get(position));

    }

    @Override
    public int getItemCount() {
        return mListOfTracks.size();
    }

}
