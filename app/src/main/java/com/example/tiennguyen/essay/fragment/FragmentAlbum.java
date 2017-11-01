package com.example.tiennguyen.essay.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiennguyen.essay.Adapter.AlbumAdapter;
import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.interfaces.ScreenShotable;
import com.example.tiennguyen.essay.model.AlbumItem;
import com.example.tiennguyen.essay.model.SingerItem;
import com.example.tiennguyen.essay.service.BaseURI;
import com.example.tiennguyen.essay.service.GetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TIENNGUYEN on 10/10/2017.
 */

public class FragmentAlbum extends Fragment implements ScreenShotable, View.OnClickListener {
    BaseURI baseURI;
    String albumListLink = "";
    TextView textView;
    GetData getdata;

    ArrayList<HashMap<String, String>> albumList;
    private RecyclerView rccViewAlbum;
    private AlbumAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Button btnVietNam, btnUS, btnKorea, btnOther;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, viewGroup, false);

        btnVietNam = (Button) view.findViewById(R.id.btnVietNam);
        btnUS = (Button) view.findViewById(R.id.btnUs);
        btnKorea = (Button) view.findViewById(R.id.btnKorea);
        btnOther = (Button) view.findViewById(R.id.btnOther);

//        btnVietNam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new FragmentAlbumExpand();
//                Bundle bundle = new Bundle();
//                bundle.putInt("type", 1);
//                fragment.setArguments(bundle);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
//            }
//        });
        btnVietNam.setOnClickListener(this);
        btnUS.setOnClickListener(this);
        btnKorea.setOnClickListener(this);
        btnOther.setOnClickListener(this);

        rccViewAlbum = (RecyclerView) view.findViewById(R.id.rccViewAlbum);
        rccViewAlbum.setHasFixedSize(true);

//        LinearLayoutManager llm = new LinearLayoutManager(getContext());
//        rccViewAlbum.setLayoutManager(llm);

        layoutManager = new LinearLayoutManager(getContext());
        rccViewAlbum.setLayoutManager(layoutManager);

        initVar();


        final ArrayList<AlbumItem> arrAlbum = new ArrayList<>();

        getdata.setDataDownloadListener(new GetData.DataDownloadListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void dataDownloadedSuccessfully(JSONObject data) {
                try {

                    JSONArray albumListJSON = data.getJSONArray("list");
                    for(int albIndex = 0; albIndex < albumListJSON.length() ; albIndex++){
                        JSONObject album = albumListJSON.getJSONObject(albIndex);
                        String title = album.getString("title");
                        String img = album.getString("img");
                        String href = album.getString("href");
                        JSONArray singersJSON = album.getJSONArray("singers");
                        ArrayList<SingerItem> arrSinger = new ArrayList<SingerItem>();
                        for (int singerIndex = 0; singerIndex < singersJSON.length(); singerIndex++ ){
                            JSONObject singer = singersJSON.getJSONObject(singerIndex);
                            String singerName = singer.getString("singerName");
                            String singerHref = singer.getString("singerHref");
                            SingerItem singerItem = new SingerItem(singerName, singerHref);
                            arrSinger.add(singerItem);
                        }

                        AlbumItem albumItem = new AlbumItem(title, arrSinger, img, href);

                        arrAlbum.add(albumItem);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                try {
//                    JSONArray albumListJSON = data.getJSONArray("list");
//                    textView.setText(albumListJSON.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                Drawable divider = getResources().getDrawable(R.drawable.divider);
                RecyclerView.ItemDecoration dividerItemDecoration = new AlbumAdapter.DividerItemDecoration(divider);

                adapter = new AlbumAdapter(getContext(), arrAlbum, true);
                rccViewAlbum.addItemDecoration(dividerItemDecoration);
                rccViewAlbum.setAdapter(adapter);

//                RVAdapter adapter = new RVAdapter(arrAlbum, getContext());
//                rccViewAlbum.setAdapter(adapter);

            }

            @Override
            public void dataDownloadFailed() {
                // handler failure (e.g network not available etc.)
            }
        });


        getdata.execute(albumListLink);

        return view;
    }


    private void initVar(){
        baseURI = new BaseURI();
        getdata = new GetData(getContext());
        albumListLink = baseURI.getAlbumHot().toString();

    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    @Override
    public void onClick(View v) {
        int type = 0;
        switch (v.getId()){
            case R.id.btnVietNam: type = 1; break;
            case R.id.btnUs: type = 2; break;
            case R.id.btnKorea: type = 3; break;
            case R.id.btnOther: type = 4; break;
        }

        Fragment fragment = new FragmentAlbumExpand();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
}
