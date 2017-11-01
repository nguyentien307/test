package com.example.tiennguyen.essay.model;

import java.util.ArrayList;

/**
 * Created by TIENNGUYEN on 10/27/2017.
 */

public class SongItem {
    private String title;
    private ArrayList<SingerItem> singers;
    private String img;
    private String href;

    public SongItem(String title, ArrayList<SingerItem> singers, String img, String href){
        this.title = title;
        this.singers = singers;
        this.img = img;
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<SingerItem> getSingers() {
        return singers;
    }

    public void setSingers(ArrayList<SingerItem> singers) {
        this.singers = singers;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
