package com.example.tiennguyen.essay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiennguyen.essay.Adapter.SongAdapter;
import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.model.AlbumItem;
import com.example.tiennguyen.essay.model.SingerItem;
import com.example.tiennguyen.essay.model.SongItem;
import com.example.tiennguyen.essay.service.BaseURI;
import com.example.tiennguyen.essay.service.GetData;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentAlbumSongs extends Fragment {
    private static final String ALBUM_ITEM = "albItem";

    private AlbumItem albumItem;
    private String mParam2;

    ImageView imgViewAlbumBG;
    RecyclerView rccViewAlbumSongs;
    TextView tvAlbumTitle, tvAlbumSingers;
    LinearLayout llBackground;
    ArrayList<SongItem> songList = new ArrayList<>();
    BaseURI baseURI = new BaseURI();

    public FragmentAlbumSongs() {
        // Required empty public constructor
    }

    public static FragmentAlbumSongs newInstance(AlbumItem albumItem) {
        FragmentAlbumSongs fragment = new FragmentAlbumSongs();
        Bundle args = new Bundle();
        args.putSerializable(ALBUM_ITEM, albumItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            albumItem = (AlbumItem) getArguments().getSerializable(ALBUM_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album_songs, container, false);
        imgViewAlbumBG = (ImageView) view.findViewById(R.id.imgViewAlbumBG);
        Picasso.with(getContext())
                .load(albumItem.getImg())
                .placeholder(R.drawable.item_down)
                .error(R.drawable.item_up)
                .into(imgViewAlbumBG);
        tvAlbumTitle = (TextView) view.findViewById(R.id.tvAlbumTitle);
        tvAlbumSingers = (TextView) view.findViewById(R.id.tvAlbumSingers);

        tvAlbumTitle.setText(albumItem.getTitle());

        ArrayList<SingerItem> singers =  albumItem.getSingers();
        String singerName = "";
        for (int singerIndex = 0; singerIndex < singers.size(); singerIndex ++ ){
            if (singerIndex == singers.size() - 1){
                singerName += singers.get(singerIndex).getSingerName();
            }
            else {
                singerName += singers.get(singerIndex).getSingerName() + ", ";
            }
        }
        if(singerName!="") {
            tvAlbumSingers.setText(singerName);
        }
        else tvAlbumSingers.setText("khong co");

//        View backgroundimage = view.findViewById(R.id.llBackground);
//        Drawable background = backgroundimage.getBackground();
//        background.setAlpha(80);

        rccViewAlbumSongs = (RecyclerView) view.findViewById(R.id.rccViewAlbumSongs);
        rccViewAlbumSongs.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rccViewAlbumSongs.setLayoutManager(llm);

        setData(albumItem.getHref());

        return view;
    }

    private void setData(String href) {
        GetData getdata = new GetData(getContext());
        String link = baseURI.getSongListAlbum(href);

        getdata.setDataDownloadListener(new GetData.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(JSONObject data) {
                try{
                    JSONArray songListJSON = data.getJSONArray("list");
                    for(int songIndex = 0; songIndex < songListJSON.length() ; songIndex++) {
                        JSONObject song = songListJSON.getJSONObject(songIndex);
                        String title = song.getString("title");
                        //String img = song.getString("img");
                        String img = "";
                        String href = song.getString("href");
                        JSONArray singersJSON = song.getJSONArray("singers");
                        ArrayList<SingerItem> arrSinger = new ArrayList<SingerItem>();
                        for (int singerIndex = 0; singerIndex < singersJSON.length(); singerIndex++) {
                            JSONObject singer = singersJSON.getJSONObject(singerIndex);
                            String singerName = singer.getString("singerName");
                            String singerHref = singer.getString("singerHref");
                            SingerItem singerItem = new SingerItem(singerName, singerHref);
                            arrSinger.add(singerItem);
                        }

                        SongItem songItem = new SongItem(title, arrSinger, img, href);
                        songList.add(songItem);
                }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SongAdapter adapter = new SongAdapter(songList, getContext(), true);
                rccViewAlbumSongs.setAdapter(adapter);
            }

            @Override
            public void dataDownloadFailed() {

            }
        });
        getdata.execute(link);
    }

}
