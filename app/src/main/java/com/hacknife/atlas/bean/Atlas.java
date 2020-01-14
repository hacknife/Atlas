package com.hacknife.atlas.bean;

import com.hacknife.atlas.helper.StringHelper;

import org.jsoup.internal.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Atlas {

    String title;
    String cover;
    String url;

    public Atlas(String title, String cover, String url) {
        this.title = title;
        this.cover = cover;
        this.url = url;
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
                '}';
    }

    public String getUrl() {
        return StringHelper.link(AtlasResource.get().page_url, url);
    }
}
