package com.example.tiennguyen.essay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiennguyen.essay.R;
import com.example.tiennguyen.essay.model.AlbumItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TIENNGUYEN on 10/14/2017.
 */

public class AlbumAdapter extends ArrayAdapter<AlbumItem> {

    private Context context;
    private int resource;
    private List<AlbumItem> arrAlbum;

    public AlbumAdapter(Context context, int resource, ArrayList<AlbumItem> arrAlbum) {
        super(context, resource, arrAlbum);
        this.context = context;
        this.resource = resource;
        this.arrAlbum = arrAlbum;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_list_item, parent, false);
            viewHolder = new ViewHolder();
            //ViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvSingers = (TextView) convertView.findViewById(R.id.tvSingers);
            //viewHolder.imgView = (ImageView) convertView.findViewById(R.id.tvAvatar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AlbumItem albumItem = arrAlbum.get(position);
        //viewHolder.tvAvatar.setBackgroundColor(contact.getColor());
       // viewHolder.tvAvatar.setText(String.valueOf(position+1));
        //viewHolder.tvName.setText(contact.getName());
        viewHolder.tvSingers.setText(albumItem.getSingers());
        return convertView;
    }

    public class ViewHolder {
        TextView tvTitle;
        //TextView tvTitle;
        TextView tvSingers;
        ImageView imgView;

    }
}
