package com.hacknife.atlas.bean;

import java.util.List;

public class ImageCollection {
    Integer cached;
    String next;
    List<String> images;

    public String getNext() {
        return next;
    }

    public List<String> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "{" +
                "\"next\":\'" + next + "\'" +
                ", \"images\":" + images +
                '}';
    }

    public ImageCollection(String next, List<String> images) {
        this.next = next;
        this.images = images;
    }

    public ImageCollection(String next, List<String> images, Integer cached) {
        this.next = next;
        this.images = images;
        this.cached = cached;
    }

    public boolean cached() {
        return cached != null && (cached == 1);
    }

    public void setNext(String next) {
        this.next = next;
    }
}
