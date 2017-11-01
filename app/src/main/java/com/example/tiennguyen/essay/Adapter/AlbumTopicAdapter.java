package com.example.tiennguyen.essay.Adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.fragment.FragmentAlbumTopic;
import com.example.tiennguyen.essay.interfaces.ItemClickListener;
import com.example.tiennguyen.essay.model.AlbumTopicItem;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.ArrayList;

/**
 * Created by TIENNGUYEN on 10/17/2017.
 */

public class AlbumTopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<AlbumTopicItem> arrAlbumTopicItems;
    Context context;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public AlbumTopicAdapter(ArrayList<AlbumTopicItem> arrAlbumTopicItems){
        this.arrAlbumTopicItems = arrAlbumTopicItems;
        for(int i = 0; i < arrAlbumTopicItems.size(); i++){
            expandState.append(i, false);
        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        if(arrAlbumTopicItems.get(position).isExpandable()){
//            return 1;
//        }
//        else{
//            return 0;
//        }
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.album_expand_with_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder)holder;
        AlbumTopicItem item = arrAlbumTopicItems.get(position);
        viewHolder.setIsRecyclable(false);
        viewHolder.tvTopicTitle.setText(item.getTopicTitle());
        viewHolder.expandableLayout.setInRecyclerView(true);
        viewHolder.expandableLayout.setExpanded(expandState.get(position));
        viewHolder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {

            @Override
            public void onPreOpen() {
                changeRotate(viewHolder.button,0f,180f).start();
                expandState.put(position,true);
            }

            @Override
            public void onPreClose() {
                changeRotate(viewHolder.button,180f,0f).start();
                expandState.put(position,false);
            }

        });
        viewHolder.button.setRotation(expandState.get(position)?180f:0f);
//        viewHolder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewHolder.expandableLayout.toggle();
//            }
//        });

        //---------------------------
        viewHolder.tvTopicDetail.setText(item.getTopicTitle());
        viewHolder.tvTopicDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, arrAlbumTopicItems.get(position).getTopicTitle(), Toast.LENGTH_SHORT).show();
                FragmentAlbumTopic fragment = new FragmentAlbumTopic();
                Bundle bundle = new Bundle();
                bundle.putString("href", arrAlbumTopicItems.get(position).getHref());
                fragment.setArguments(bundle);
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

            }
        });
        //----------------------------

        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //String link = arrAlbumTopicItems.get(position).getHref();
                //String link = "https://web-service-app.herokuapp.com/album/album-hot-list";
                //getData(link, position, viewHolder);

                viewHolder.expandableLayout.toggle();
                //Toast.makeText(context, "choose" + arrAlbumTopicItems.get(position).getTopicTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);

        viewHolder.rccViewAlbum.setLayoutManager(layoutManager);
        viewHolder.rccViewAlbum.setHasFixedSize(true);


        //viewHolder.rccViewAlbum.setLayoutManager(layoutManager);

        viewHolder.rccViewAlbum.addItemDecoration(new AlbumAdapter.GridSpacingItemDecoration(2, dpToPx(10), true));
        viewHolder.rccViewAlbum.setNestedScrollingEnabled(false);
        viewHolder.rccViewAlbum.setItemAnimator(new DefaultItemAnimator());
        AlbumAdapter adapter = new AlbumAdapter(context, item.getArrAlbum(), false);
        viewHolder.rccViewAlbum.setAdapter(adapter);

    }



    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "rotation", from, to );
        animator.setDuration(400);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount() {
        return arrAlbumTopicItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvTopicTitle, tvTopicDetail;
        public RelativeLayout button;
        public ExpandableLinearLayout expandableLayout;
        ItemClickListener itemClickListener;
        public RecyclerView rccViewAlbum;


        public ViewHolder(View itemView) {
            super(itemView);

            tvTopicTitle = (TextView) itemView.findViewById(R.id.tvTopicTitle);
            tvTopicDetail = (TextView) itemView.findViewById(R.id.tvTopicDetail);
            button = (RelativeLayout) itemView.findViewById(R.id.button);
            expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);
            rccViewAlbum = (RecyclerView) itemView.findViewById(R.id.rccViewAlbum);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }

    private int dpToPx(int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}



