package com.hacknife.atlas.bean;

import com.hacknife.atlas.http.HttpClient;

import java.util.Arrays;
import java.util.List;

public class AtlasResource {
    private static AtlasResource sAtlasResource;
    /**
     * https://www.999mm.cn/
     */
    public String host;
    /**
     * https://www.999mm.cn/a/guochanmeinv/
     */
    public String atlas;
    /**
     * list_4_%d.html
     */
    public String page_url;
    /**
     * 套图选择器
     */
    public String[] atlasSelect;
    /**
     *
     */
    public String[] atlasTitle;
    public String[] atlasCover;
    public String[] atlasUrl;

    public static AtlasResource init(String host, String atlas, String page_url, String[] atlasSelect, String[] atlasTitle, String[] atlasCover, String[] atlasUrl) {
        AtlasResource resource = get();
        resource.host = host;
        resource.atlas = atlas;
        resource.page_url = page_url;
        resource.atlasSelect = atlasSelect;
        resource.atlasTitle = atlasTitle;
        resource.atlasCover = atlasCover;
        resource.atlasUrl = atlasUrl;
        HttpClient.refresh();
        return resource;
    }

    public static AtlasResource get() {
        if (sAtlasResource == null)
            synchronized (AtlasResource.class) {
                if (sAtlasResource == null)
                    sAtlasResource = new AtlasResource();
            }
        return sAtlasResource;
    }

    @Override
    public String toString() {
        return "{" +
                "\"host\":\'" + host + "\'" +
                ", \"atlas\":\'" + atlas + "\'" +
                ", \"page_url\":\'" + page_url + "\'" +
                ", \"atlasSelect\":" + Arrays.toString(atlasSelect) +
                ", \"atlasTitle\":" + Arrays.toString(atlasTitle) +
                ", \"atlasCover\":" + Arrays.toString(atlasCover) +
                ", \"atlasUrl\":" + Arrays.toString(atlasUrl) +
                '}';
    }
}
