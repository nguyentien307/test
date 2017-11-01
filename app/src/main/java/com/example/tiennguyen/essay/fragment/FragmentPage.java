package com.example.tiennguyen.essay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiennguyen.essay.Adapter.SongAdapter;
import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.model.SingerItem;
import com.example.tiennguyen.essay.model.SongItem;
import com.example.tiennguyen.essay.service.BaseURI;
import com.example.tiennguyen.essay.service.GetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TIENNGUYEN on 10/12/2017.
 */

public class FragmentPage extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    BaseURI baseURI = new BaseURI();
    String songChartLink = baseURI.getSongTop(true);
    RecyclerView rvSongList;
    ArrayList<SongItem> songList = new ArrayList<>();


    private int mPage;

    public static FragmentPage newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentPage fragment = new FragmentPage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //songList.clear();
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
//        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
//        tvTitle.setText("Fragment #" + mPage);

        rvSongList = (RecyclerView) view.findViewById(R.id.rvSongList);
        rvSongList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvSongList.setLayoutManager(llm);

        setData(mPage);

        return view;
    }

    private void setData(final int position) {
        GetData getdata = new GetData(getContext());

        getdata.setDataDownloadListener(new GetData.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(JSONObject data) {
                JSONObject objectChart = null;
                switch(position){
                    case 1: objectChart = getObjectJSON(data, "topViet");break;
                    case 2: objectChart = getObjectJSON(data, "topAuMy");break;
                    case 3: objectChart = getObjectJSON(data, "topKorea");break;
                }

                try {

                    JSONArray songListJSON = objectChart.getJSONArray("list");
                    for(int songIndex = 0; songIndex < songListJSON.length() ; songIndex++){
                        JSONObject song = songListJSON.getJSONObject(songIndex);
                        String title = song.getString("title");
                        String img = song.getString("img");
                        String href = song.getString("href");
                        JSONArray singersJSON = song.getJSONArray("singers");
                        ArrayList<SingerItem> arrSinger = new ArrayList<SingerItem>();
                        for (int singerIndex = 0; singerIndex < singersJSON.length(); singerIndex++ ){
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

                SongAdapter adapter = new SongAdapter(songList, getContext(), false);
                rvSongList.setAdapter(adapter);
            }

            @Override
            public void dataDownloadFailed() {

            }
        });
        getdata.execute(songChartLink);
    }

    private JSONObject getObjectJSON(JSONObject data, String type) {
        try {
            JSONObject topicTitle = data.getJSONObject(type);
            return topicTitle;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
