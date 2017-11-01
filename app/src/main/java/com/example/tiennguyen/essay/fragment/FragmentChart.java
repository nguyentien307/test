package com.example.tiennguyen.essay.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.interfaces.ScreenShotable;

/**
 * Created by TIENNGUYEN on 10/10/2017.
 */

public class FragmentChart extends Fragment implements ScreenShotable, View.OnClickListener{

    LinearLayout songChart, songTop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, viewGroup, false);

        songChart = (LinearLayout) view.findViewById(R.id.songChart);
        songTop = (LinearLayout) view.findViewById(R.id.songTop);

        songChart.setOnClickListener(this);
        songTop.setOnClickListener(this);

        return view;
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
        switch (v.getId()){
            case R.id.songChart:{
                Fragment fragment = new FragmentSongChart();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            }; break;
            case R.id.songTop: {

            };break;

        }
    }
}