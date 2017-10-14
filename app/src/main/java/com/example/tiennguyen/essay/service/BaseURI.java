package com.example.tiennguyen.essay.service;

/**
 * Created by Quyen Hua on 10/8/2017.
 */

public class BaseURI {
    private String NAME_SPACE = "https://web-service-app.herokuapp.com/";
    private String SONG_TOP = "top-song/top-list";
    private String SONG_TOP_A_TITLE = "top-song/top-song";
    private String ALBUM_HOT = "album/album-hot-list";
    private String SONG_LIST_A_ALBUM = "album/album-song-list";
    private String MV_HOT = "mv/mv-hot-list";
    private String SONG_LIST_A_TITLE = "mv/mv-of-title";
    private String SONG_LIST_A_MV = "mv/mv-of-title/items/";
    private String MV_TOP = "mv/mv-top-list";
    private String MV_TOP_A_TITLE = "mv/song-mv-top";
    private String NEW_SINGER_SONG = "new/new-singer-song-list";
    private String TOPIC = "topic/topic-list";
    private String SONG_A_TOPIC = "topic/song-of-topic";
    private String SEARCHED_SONG = "search-song";
    private String SONG_INFOMATION = "song/song-info";

    public String getNameSpace () {
        return NAME_SPACE;
    }

    public String getSongTop() {
        return this.getNameSpace() + SONG_TOP;
    }

    public String getSongTop (boolean hasItems) {
        return this.getSongTop() + "?getItems=" + hasItems;
    }

    public String getSongTopATile (String url) {
        return this.getNameSpace() + SONG_TOP_A_TITLE + "?url=" + url;
    }

    public String getAlbumHot () {
        return this.getNameSpace() + ALBUM_HOT;
    }

    public String getSongListAlbum (String url) {
        return this.getNameSpace() + SONG_LIST_A_ALBUM + "?url=" + url;
    }

    public String getMVHot () {
        return this.getNameSpace() + MV_HOT;
    }

    public String getMVHot (boolean hasItems) {
        return this.getMVHot() + "?getItems=" + hasItems;
    }

    public String getSongListATitle (String url) {
        return this.getNameSpace() + SONG_LIST_A_TITLE + "?url=" + url;
    }

    public String getSongListATitle (String url, boolean hasItems) {
        return this.getSongListATitle(url) + "&getItems=" + hasItems;
    }

    public String getSongListAMV (String url) {
        return this.getNameSpace() + SONG_LIST_A_MV + "all?url=" + url;
    }

    public String getSongListAMV (String url, int nums) {
        return this.getNameSpace() + SONG_LIST_A_MV + nums + "?url=" + url;
    }

    public String getMVTop () {
        return this.getNameSpace() + MV_TOP;
    }

    public String getMVTop (boolean hasItems) {
        return this.getMVTop() + "?getItems" + hasItems;
    }

    public String getMVTopATitle (String url) {
        return this.getNameSpace() + MV_TOP_A_TITLE + "?url=" + url;
    }

    public String getNewSingerSong () {
        return this.getNameSpace() + NEW_SINGER_SONG;
    }

    public String getTopic () {
        return this.getNameSpace() + TOPIC;
    }

    public String getSongATopic (String url) {
        return this.getNameSpace() + SONG_A_TOPIC + "?url=" + url;
    }

    public String getSearchedSong (String songName) {
        return this.getNameSpace() + SEARCHED_SONG + "?songName=" + songName;
    }

    public String getSongInfomation (String url) {
        return this.getNameSpace() + SONG_INFOMATION + "?url=" + url;
    }
}
