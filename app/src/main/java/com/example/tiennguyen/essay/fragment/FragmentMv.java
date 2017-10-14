package com.example.tiennguyen.essay.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.interfaces.ScreenShotable;

/**
 * Created by TIENNGUYEN on 10/10/2017.
 */

public class FragmentMv extends Fragment implements ScreenShotable {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mv, viewGroup, false);

        return view;
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}