package com.example.tiennguyen.essay.model;

/**
 * Created by TIENNGUYEN on 10/23/2017.
 */

public class TopicTitle {
    private String title;
    private String href;

    public TopicTitle(String title, String href) {
        this.title = title;
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
