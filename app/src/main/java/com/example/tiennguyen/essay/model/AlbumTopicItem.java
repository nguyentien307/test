package com.example.tiennguyen.essay.model;

import java.util.ArrayList;

/**
 * Created by TIENNGUYEN on 10/17/2017.
 */

public class AlbumTopicItem {
    private String topicTitle;
    private String href;
    private ArrayList<AlbumItem> arrAlbum;
    //private boolean isExpandable;

//    public AlbumTopicItem(String topicTitle, ArrayList<AlbumItem> arrAlbum, boolean isExpandable) {
//        this.topicTitle = topicTitle;
//        this.arrAlbum = arrAlbum;
//        //this.isExpandable = isExpandable;
//    }
    public AlbumTopicItem(String topicTitle, String href, ArrayList<AlbumItem> arrAlbum) {
        this.topicTitle = topicTitle;
        this.href = href;
        this.arrAlbum = arrAlbum;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public ArrayList<AlbumItem> getArrAlbum() {
        return arrAlbum;
    }

    public void setArrAlbum(ArrayList<AlbumItem> arrAlbum) {
        this.arrAlbum = arrAlbum;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

//    public boolean isExpandable() {
//        return isExpandable;
//    }
//
//    public void setExpandable(boolean expandable) {
//        isExpandable = expandable;
//    }
}
