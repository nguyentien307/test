package com.example.tiennguyen.essay.model;

/**
 * Created by TIENNGUYEN on 10/16/2017.
 */

public class SingerItem {
    private String singerName;
    private String singerHref;

    public SingerItem(String singerName, String singerHref) {
        this.singerName = singerName;
        this.singerHref = singerHref;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singers) {
        this.singerName = singerName;
    }

    public String getSingerHref() {
        return singerHref;
    }

    public void setSingerHref(String singerHref) {
        this.singerHref = singerHref;
    }
}
