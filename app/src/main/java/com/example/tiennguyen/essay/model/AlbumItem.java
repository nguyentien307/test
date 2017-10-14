package com.example.tiennguyen.essay.model;

/**
 * Created by TIENNGUYEN on 10/14/2017.
 */

public class AlbumItem {
    private String title;
    private String singers;
    private String img;
    private String href;

    public AlbumItem(String title, String singers, String img, String href) {
        this.title = title;
        this.singers = singers;
        this.img = img;
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTile(String title) {
        this.title = title;
    }

    public String getSingers() {
        return singers;
    }

    public void setSingers(String singers) {
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
