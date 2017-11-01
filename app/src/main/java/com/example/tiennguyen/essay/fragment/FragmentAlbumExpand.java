package com.example.tiennguyen.essay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiennguyen.essay.Adapter.AlbumTopicAdapter;
import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.model.AlbumItem;
import com.example.tiennguyen.essay.model.AlbumTopicItem;
import com.example.tiennguyen.essay.model.SingerItem;
import com.example.tiennguyen.essay.model.TopicTitle;
import com.example.tiennguyen.essay.service.BaseURI;
import com.example.tiennguyen.essay.service.GetData;
import com.example.tiennguyen.essay.service.GetMultiData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TIENNGUYEN on 10/17/2017.
 */

public class FragmentAlbumExpand extends Fragment {
    RecyclerView rccViewAlbumTopic;
    RecyclerView rccViewAlbum;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<AlbumTopicItem> arrAlbumTopicItems = new ArrayList<>();
    int type;
    ArrayList<AlbumItem> albumTop;
    ArrayList<TopicTitle> arrTopic = new ArrayList<>();


    BaseURI baseURI;
    String albumListTopic = "";
    GetData getdata, getDataAlbum;


    private void initVar(){
        baseURI = new BaseURI();
        getdata = new GetData(getContext());
        albumListTopic = baseURI.getAlbumTitleList().toString();


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_expand, viewGroup, false);

        rccViewAlbumTopic = (RecyclerView) view.findViewById(R.id.rccViewAlbumTopic);
        rccViewAlbumTopic.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rccViewAlbumTopic.setLayoutManager(layoutManager);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");
        }

        initVar();

        setData(type);
        //get();
        return view;
    }

    private void setData(final int type) {
        getdata.setDataDownloadListener(new GetData.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(JSONObject data) {
                JSONObject topicTitle = null;
                switch(type){
                    case 1: topicTitle = getObjectJSON(data, "VIỆT NAM");break;
                    case 2: topicTitle = getObjectJSON(data, "ÂU MỸ"); break;
                    case 3: topicTitle = getObjectJSON(data, "CHÂU Á"); break;
                    case 4: topicTitle = getObjectJSON(data, "KHÁC"); break;
                }

                try {
                    JSONArray listTopicTilte = topicTitle.getJSONArray("list");
                    ArrayList<String> arrHref = new ArrayList<String>();
                    for (int i = 0; i < listTopicTilte.length(); i++){
                        JSONObject topic = listTopicTilte.getJSONObject(i);
                        String title = topic.getString("title");
                        String href = topic.getString("href");

                        String link = baseURI.getAlbumOfATitle(href,1,4);
                        arrHref.add(link);
                        TopicTitle topicItem = new TopicTitle(title, href);
                        arrTopic.add(topicItem);

                        //AlbumTopicItem item = new AlbumTopicItem(title, href, null);
                        //arrAlbumTopicItems.add(item);
                    }

                    getAlbumTop(arrTopic, arrHref);
                    //AlbumTopicAdapter adapter = new AlbumTopicAdapter(arrAlbumTopicItems);
                    //rccViewAlbumTopic.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void dataDownloadFailed() {

            }
        });
        getdata.execute(albumListTopic);

    }

    private void getAlbumTop(final ArrayList<TopicTitle> arrTopic, ArrayList<String> arrHref) {
        GetMultiData getAlbum = new GetMultiData(getContext());


        getAlbum.setDataDownloadListener(new GetMultiData.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(JSONArray data) {

                try {
                    for (int i = 0; i < data.length(); i++) {
                        JSONArray albumListJSON = data.getJSONObject(i).getJSONArray("list");
                        ArrayList<AlbumItem> arrAlbum = new ArrayList<AlbumItem>();
                        for (int albIndex = 0; albIndex < albumListJSON.length(); albIndex++) {
                            JSONObject album = albumListJSON.getJSONObject(albIndex);
                            String title = album.getString("title");
                            String img = album.getString("img");
                            String href = album.getString("href");
                            JSONArray singersJSON = album.getJSONArray("singers");
                            ArrayList<SingerItem> arrSinger = new ArrayList<SingerItem>();
                            for (int singerIndex = 0; singerIndex < singersJSON.length(); singerIndex++) {
                                JSONObject singer = singersJSON.getJSONObject(singerIndex);
                                String singerName = singer.getString("singerName");
                                String singerHref = singer.getString("singerHref");
                                SingerItem singerItem = new SingerItem(singerName, singerHref);
                                arrSinger.add(singerItem);
                            }

                            AlbumItem albumItem = new AlbumItem(title, arrSinger, img, href);
                            arrAlbum.add(albumItem);

                        }

                        AlbumTopicItem item = new AlbumTopicItem(arrTopic.get(i).getTitle(), arrTopic.get(i).getHref(), arrAlbum);
                        arrAlbumTopicItems.add(item);


                    }

                    AlbumTopicAdapter adapter = new AlbumTopicAdapter(arrAlbumTopicItems);
                    rccViewAlbumTopic.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void dataDownloadFailed() {

            }
        });

        getAlbum.execute(arrHref);
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
