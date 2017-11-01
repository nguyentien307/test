package com.example.tiennguyen.essay.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiennguyen.essay.Adapter.AlbumAdapter;
import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.model.AlbumItem;
import com.example.tiennguyen.essay.model.SingerItem;
import com.example.tiennguyen.essay.service.BaseURI;
import com.example.tiennguyen.essay.service.GetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TIENNGUYEN on 10/23/2017.
 */

public class FragmentAlbumTopic extends Fragment {

    private RecyclerView rccViewAlbum;
    private AlbumAdapter adapter;
    private ArrayList<AlbumItem> albumList;
    private String href;
    TextView text;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_topic, viewGroup, false);

        rccViewAlbum = (RecyclerView) view.findViewById(R.id.rccViewAlbum1);
        text = (TextView) view.findViewById(R.id.textView);
        albumList = new ArrayList<>();

        layoutManager = new GridLayoutManager(getContext(), 2);

        rccViewAlbum.setLayoutManager(layoutManager);
        rccViewAlbum.setHasFixedSize(true);


        //viewHolder.rccViewAlbum.setLayoutManager(layoutManager);

        rccViewAlbum.addItemDecoration(new AlbumAdapter.GridSpacingItemDecoration(2, dpToPx(10), true));
        rccViewAlbum.setNestedScrollingEnabled(false);
        rccViewAlbum.setItemAnimator(new DefaultItemAnimator());
        adapter = new AlbumAdapter(getContext(), albumList, false);
        rccViewAlbum.setAdapter(adapter);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            href = bundle.getString("href");
        }

//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
//        rccViewAlbum.setLayoutManager(mLayoutManager);
//        rccViewAlbum.setHasFixedSize(true);
//        rccViewAlbum.addItemDecoration(new AlbumAdapter.GridSpacingItemDecoration(2, dpToPx(10), true));
//        rccViewAlbum.setItemAnimator(new DefaultItemAnimator());
//        rccViewAlbum.setAdapter(adapter);
        //Toast.makeText(getActivity(), href, Toast.LENGTH_SHORT).show();
        prepareAlbums(href);

        return view;
    }

    private void prepareAlbums(String href) {
        GetData getData = new GetData(getContext());
        BaseURI baseURI = new BaseURI();
        String link = baseURI.getAlbumOfATitle(href, 1, 10);
        getData.setDataDownloadListener(new GetData.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(JSONObject data) {
                try {

                    JSONArray albumListJSON = data.getJSONArray("list");
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

                        albumList.add(albumItem);

                    }



//                    AlbumAdapter adapter = new AlbumAdapter(getContext(), albumList, false);
//                    rccViewAlbum.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void dataDownloadFailed() {

            }
        });
        getData.execute(link);
    }

    private int dpToPx(int dp) {
        Resources r = getContext().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
