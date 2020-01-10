package com.hacknife.atlas.bean;

import com.hacknife.atlas.http.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class Atlas {
    String id;
    String title;
    List<Image> images;

    @Override
    public String toString() {
        return "{" +
                "\"id\":\'" + id + "\'" +
                ", \"title\":\'" + title + "\'" +
                ", \"images\":" + images +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return String.format("%simage?atlas=%s&img=%s", HttpClient.BASE_URL, id, images.get(0).id);
    }

    public List<String> getImage() {
        List<String> urls = new ArrayList<>(images.size());
        images.forEach(image -> urls.add(String.format("%simage?atlas=%s&img=%s", HttpClient.BASE_URL, id, image.id)));
        return urls;
    }
}
