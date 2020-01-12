package com.hacknife.atlas.bean;

import java.util.ArrayList;
import java.util.List;

public class Atlas {

    String title;
    String cover;
    String url;
    List<String> images;

    public Atlas(String title, String cover, String url) {
        this.title = title;
        this.cover = cover;
        this.url = url;
        this.images = new ArrayList<>();
    }

    public String getCover() {
        return cover.startsWith("/") ? AtlasResource.get().host + cover : cover;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "{" +
                "\"title\":\'" + title + "\'" +
                ", \"cover\":\'" + cover + "\'" +
                ", \"url\":\'" + url + "\'" +
                ", \"images\":" + images +
                '}';
    }
}
