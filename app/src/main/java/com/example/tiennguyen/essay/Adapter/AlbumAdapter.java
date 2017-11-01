package com.example.tiennguyen.essay.Adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.fragment.FragmentAlbumSongs;
import com.example.tiennguyen.essay.model.AlbumItem;
import com.example.tiennguyen.essay.model.SingerItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TIENNGUYEN on 10/14/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.RecyclerViewHolder> {

    private Context context;
    private List<AlbumItem> arrAlbum;
    private Boolean isVertical;

    public AlbumAdapter(Context context, ArrayList<AlbumItem> arrAlbum, Boolean isVertical) {
        this.context = context;
        this.arrAlbum = arrAlbum;
        this.isVertical = isVertical;
    }

    public void updateList(ArrayList<AlbumItem> arrAlbum) {
        this.arrAlbum = arrAlbum;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.arrAlbum.size();
        //return 0;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView;
        if (isVertical){
            itemView = inflater.inflate(R.layout.row_list_item, viewGroup, false);
        }
        else {
            itemView = inflater.inflate(R.layout.album_card, viewGroup, false);
        }

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/mystical.ttf");
        viewHolder.tvTitle.setTypeface(typeface);
        viewHolder.tvTitle.setText(arrAlbum.get(position).getTitle());
        ArrayList<SingerItem> singers =  arrAlbum.get(position).getSingers();
        String singerName = "";
        for (int singerIndex = 0; singerIndex < singers.size(); singerIndex ++ ){
            singerName += singers.get(singerIndex).getSingerName() + " ";
        }

        viewHolder.tvSingers.setText(singerName);

        Picasso.with(context)
                .load(arrAlbum.get(position).getImg())
                .placeholder(R.drawable.item_down)
                .error(R.drawable.item_up)
                .into(viewHolder.imgView);
    }

    public void addItem(int position, AlbumItem albumItem) {
        arrAlbum.add(position, albumItem);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        arrAlbum.remove(position);
        notifyItemRemoved(position);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvTitle, tvSingers;
        public ImageView imgView;

        public RecyclerViewHolder(final View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSingers = (TextView) itemView.findViewById(R.id.tvSingers);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
            itemView.setOnClickListener(this);
//            imgView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemView.setOnClickListener(this);
//                }
//            });
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(context, arrAlbum.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            FragmentAlbumSongs fragment = FragmentAlbumSongs.newInstance(arrAlbum.get(getAdapterPosition()));
            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    public static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private Drawable mDivider;

        public DividerItemDecoration(Drawable divider) {
            mDivider = divider;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            if (parent.getChildAdapterPosition(view) == 0) {
                return;
            }

            outRect.top = mDivider.getIntrinsicHeight();
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            int dividerLeft = parent.getPaddingLeft();
            int dividerRight = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int dividerTop = child.getBottom() + params.bottomMargin;
                int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                mDivider.draw(canvas);
            }
        }
    }


    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */

}
