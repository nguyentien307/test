package com.example.tiennguyen.essay.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiennguyen.essay.Adapter.AlbumAdapter;
import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.interfaces.ScreenShotable;
import com.example.tiennguyen.essay.model.AlbumItem;
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

public class FragmentAlbum extends Fragment implements ScreenShotable {
    BaseURI baseURI;
    String albumListLink = "";
    TextView textView;
    GetData getdata;
    ArrayList<HashMap<String, String>> albumList;
    ListView listViewAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, viewGroup, false);

        listViewAlbum = (ListView) view.findViewById(R.id.listViewAlbum);

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
                        String singers = album.getJSONArray("singers").toString();
//                        JSONArray singers = album.getJSONArray("singers");
//                        for (int singerIndex = 0; singerIndex < singers.length(); singerIndex++ ){
//
//                        }

                       AlbumItem albumItem = new AlbumItem(title, singers, img, href);

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


                AlbumAdapter albumAdaper = new AlbumAdapter(getActivity(),R.layout.row_list_item,arrAlbum);
                listViewAlbum.setAdapter(albumAdaper);

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
        getdata = new GetData();
        albumListLink = baseURI.getAlbumHot().toString();


    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
