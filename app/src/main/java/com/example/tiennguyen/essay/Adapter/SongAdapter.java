package com.example.tiennguyen.essay.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.model.SingerItem;
import com.example.tiennguyen.essay.model.SongItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.tiennguyen.essay.R.layout.song_card;

/**
 * Created by TIENNGUYEN on 10/27/2017.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    ArrayList<SongItem> songList;
    Context ctx;
    Boolean isInAlbum;

    public SongAdapter (ArrayList<SongItem> songList, Context ctx, Boolean isInAlbum){
        this.songList = songList;
        this.ctx = ctx;
        this.isInAlbum = isInAlbum;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (isInAlbum){
            itemView = inflater.inflate(R.layout.album_song, parent, false);
        }
        else {
            itemView = inflater.inflate(song_card, parent, false);
        }
        SongViewHolder svh = new SongViewHolder(itemView);
        return svh;
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/mystical.ttf");
        holder.tvSongTitle.setTypeface(typeface);
        holder.tvSongTitle.setText(songList.get(position).getTitle());

        if (isInAlbum) {
            holder.tvIndex.setText(position + 1 +"");
        } else {
            ArrayList<SingerItem> singers = songList.get(position).getSingers();
            String singerName = "";
            for (int singerIndex = 0; singerIndex < singers.size(); singerIndex++) {
                if (singerIndex == singers.size() - 1) {
                    singerName += singers.get(singerIndex).getSingerName();
                } else {
                    singerName += singers.get(singerIndex).getSingerName() + ", ";
                }
            }
            if (singerName != "") {
                holder.tvSongSinger.setText(singerName);
            } else holder.tvSongSinger.setText("khong co");
            if (songList.get(position).getImg() != "") {
                Picasso.with(ctx)
                        .load(songList.get(position).getImg())
                        .placeholder(R.drawable.item_down)
                        .error(R.drawable.item_up)
                        .into(holder.imgSong);
            }
        }
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        CardView cvSong;
        TextView tvSongTitle;
        TextView tvSongSinger;
        ImageView imgSong;
        TextView tvIndex;

        SongViewHolder(View itemView) {
            super(itemView);
            if(itemView.getId() == R.id.song_card) {
                cvSong = (CardView) itemView.findViewById(R.id.cvSong);
                tvSongTitle = (TextView) itemView.findViewById(R.id.tvSongTitle);
                tvSongSinger = (TextView) itemView.findViewById(R.id.tvSongSinger);
                imgSong = (ImageView) itemView.findViewById(R.id.imgSong);
            }
            else {
                tvSongTitle = (TextView) itemView.findViewById(R.id.tvSongTitle);
                tvIndex = (TextView) itemView.findViewById(R.id.tvIndex);
            }
        }
    }
}
