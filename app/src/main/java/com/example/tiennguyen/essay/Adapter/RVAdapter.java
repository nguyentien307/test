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
import com.example.tiennguyen.essay.model.AlbumItem;
import com.example.tiennguyen.essay.model.SingerItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TIENNGUYEN on 10/25/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.SongViewHolder>{
    ArrayList<AlbumItem> list;
    Context ctx;

    public RVAdapter (ArrayList<AlbumItem> list, Context ctx){
        this.list = list;
        this.ctx = ctx;
    }
    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_card, parent, false);
        SongViewHolder svh = new SongViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/mystical.ttf");
        holder.songName.setTypeface(typeface);
        holder.songName.setText(list.get(position).getTitle());
        ArrayList<SingerItem> singers =  list.get(position).getSingers();
        String singerName = "";
        for (int singerIndex = 0; singerIndex < singers.size(); singerIndex ++ ){
            singerName += singers.get(singerIndex).getSingerName() + " ";
        }
        if(singerName!="") {
            holder.songSingers.setText(singerName);
        }
        else holder.songSingers.setText("khong co");

        Picasso.with(ctx)
                .load(list.get(position).getImg())
                .placeholder(R.drawable.item_down)
                .error(R.drawable.item_up)
                .into(holder.songPhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView songName;
        TextView songSingers;
        ImageView songPhoto;

        SongViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvSong);
            songName = (TextView)itemView.findViewById(R.id.tvSongTitle);
            songSingers = (TextView)itemView.findViewById(R.id.tvSongSinger);
            songPhoto = (ImageView)itemView.findViewById(R.id.imgSong);
        }
    }

}