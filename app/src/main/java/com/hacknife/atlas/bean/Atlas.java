package com.hacknife.atlas.bean;

import com.hacknife.atlas.helper.StringHelper;

import org.jsoup.internal.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Atlas {

    String title;
    String cover;
    String url;
    int size;
    int current;

    public Atlas(String title, String cover, String url) {
        this.title = title;
        this.cover = cover;
        this.url = url;
    }

    public String getCover() {
        return StringHelper.link(AtlasResource.get().page_url, cover);
    }

    public String getTitle() {
        return title;
    }


    public String getProgress() {
        if (size != 0)
            return String.format("%d/%d", current, size);
        return "0/1+";
    }

    public String getUrl() {
        return StringHelper.link(AtlasResource.get().page_url, url);
    }

    public void setCurrent(int index) {
        current = index;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "{" +
                "\"title\":\'" + title + "\'" +
                ", \"cover\":\'" + getCover() + "\'" +
                ", \"url\":\'" + getUrl() + "\'" +
                '}';
    }
}
