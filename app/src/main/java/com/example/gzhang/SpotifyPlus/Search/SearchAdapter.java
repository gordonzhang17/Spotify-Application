package com.example.gzhang.SpotifyPlus.Search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gzhang.SpotifyPlus.HelperMethods;
import com.example.gzhang.SpotifyPlus.R;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Track;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<Track> mListOfTracks;
    private ItemClickListener mClickListener;

    public SearchAdapter(ArrayList<Track> listOfTracks) {
        this.mListOfTracks = listOfTracks;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_search_song, parent, false);

        SearchViewHolder vh = new SearchViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        String title = mListOfTracks.get(position).name;
        String artistsString = HelperMethods.getStringOfArtists(mListOfTracks.get(position));
        String album = mListOfTracks.get(position).album.name;

        holder.mTitle.setText(title);
        holder.mArtist.setText("Artist: " + artistsString);
        holder.mAlbum.setText("Album: " + album);
    }

    @Override
    public int getItemCount() {
        return mListOfTracks.size();
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;
        TextView mArtist;
        TextView mAlbum;

        SearchViewHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.cell_search_song_title);
            mArtist = view.findViewById(R.id.cell_search_song_artist);
            mAlbum = view.findViewById(R.id.cell_search_song_album);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
