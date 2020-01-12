package com.hacknife.atlas.bean;

import java.util.List;

public class Images {
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

    public Images(String next, List<String> images) {
        this.next = next;
        this.images = images;
    }
}
