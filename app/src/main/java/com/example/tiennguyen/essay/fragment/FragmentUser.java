package com.example.tiennguyen.essay.fragment;

/**
 * Created by TIENNGUYEN on 10/9/2017.
 */

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.interfaces.ScreenShotable;

public class FragmentUser extends Fragment implements ScreenShotable {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, viewGroup, false);
        TextView output= (TextView)view.findViewById(R.id.msg2);
        output.setText("Fragment Two");
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