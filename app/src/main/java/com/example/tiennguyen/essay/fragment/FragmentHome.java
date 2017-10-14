package com.example.tiennguyen.essay.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.interfaces.ScreenShotable;

public class FragmentHome extends Fragment implements ScreenShotable {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, viewGroup, false);
        TextView output= (TextView)view.findViewById(R.id.msg1);
        output.setText("Fragment One");
        return view;
    }


    @Override
    public void takeScreenShot() {
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
//                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                containerView.draw(canvas);
//                ContentFragment.this.bitmap = bitmap;
//            }
//        };
//
//        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}