package com.example.tiennguyen.essay.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.interfaces.ScreenShotable;

/**
 * Created by TIENNGUYEN on 10/6/2017.
 */

public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String USER = "User";
    public static final String HOME = "Home";
    public static final String CHART = "Chart";
    public static final String SONG = "Song";
    public static final String ALBUM = "Album";
    public static final String MV = "Mv";
    public static final String MOVIE = "Movie";

    private View containerView;
    protected ImageView mImageView;
    protected String res;
    private Bitmap bitmap;

    public static ContentFragment newInstance(String name) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(String.class.getName(), name);
        contentFragment.setArguments(bundle);

        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getString(String.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false) ;
//        switch(res) {
//            case "User" : rootView = inflater.inflate(R.layout.fragment_user, container, false); break;
//            case "Home" : rootView = inflater.inflate(R.layout.fragment_home, container, false); break;
//            case "Chart" :rootView = inflater.inflate(R.layout.fragment_main, container, false); break;
//            case "Song" :rootView = inflater.inflate(R.layout.fragment_main, container, false); break;
//            case "Album" :rootView = inflater.inflate(R.layout.fragment_main, container, false); break;
//            case "Mv" :rootView = inflater.inflate(R.layout.fragment_main, container, false); break;
//        }
        //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//        mImageView = (ImageView) rootView.findViewById(R.id.output);
//        mImageView.setClickable(true);
//        mImageView.setFocusable(true);
//        mImageView.setImageResource(res);

        return rootView;
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
        return bitmap;
    }
}
