package com.hacknife.atlas.bean;

import com.hacknife.atlas.http.HttpClient;

import java.util.Arrays;

public class DataSelector {
    private static DataSelector sAtlasResource;
    /**
     * 套图名称
     */
    public String name;
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
     * 套图标题
     */
    public String[] atlasTitle;
    /**
     * 套图封面
     */
    public String[] atlasCover;
    /**
     * 套图URL
     */
    public String[] atlasUrl;
    /**
     * 下一页地址
     */
    public String[] nextPageSelect;
    /**
     * 套图图片
     */
    public String[] imagesSelect;
    /**
     * 请求头
     */
    public String[] headers;
    /**
     * 启用Cookie
     */
    private Integer enableCookie;

    public static DataSelector init(DataSource lite) {
        DataSelector resource = get();
        resource.name = lite.name;
        resource.host = lite.host;
        resource.atlas = lite.atlas;
        resource.page_url = lite.page_url;
        resource.atlasSelect = lite.atlasSelect;
        resource.atlasTitle = lite.atlasTitle;
        resource.atlasCover = lite.atlasCover;
        resource.atlasUrl = lite.atlasUrl;
        resource.nextPageSelect = lite.nextPageSelect;
        resource.imagesSelect = lite.imagesSelect;
        resource.headers = lite.headers;
        resource.enableCookie = lite.enableCookie;
        HttpClient.refresh();
        return resource;
    }

    public static DataSelector init(String host, String atlas, String page_url, String[] atlasSelect, String[] atlasTitle, String[] atlasCover, String[] atlasUrl, String[] nextPageSelect, String[] imagesSelect, String[] headers, Integer enableCookie) {
        DataSelector resource = get();
        resource.host = host;
        resource.atlas = atlas;
        resource.page_url = page_url;
        resource.atlasSelect = atlasSelect;
        resource.atlasTitle = atlasTitle;
        resource.atlasCover = atlasCover;
        resource.atlasUrl = atlasUrl;
        resource.nextPageSelect = nextPageSelect;
        resource.imagesSelect = imagesSelect;
        resource.headers = headers;
        resource.enableCookie = enableCookie;
        HttpClient.refresh();
        return resource;
    }

    public boolean enableCookie() {
        return enableCookie != null && (enableCookie == 1);
    }

    public static DataSelector get() {
        if (sAtlasResource == null)
            synchronized (DataSelector.class) {
                if (sAtlasResource == null)
                    sAtlasResource = new DataSelector();
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
                ", \"nextPageSelect\":" + Arrays.toString(nextPageSelect) +
                ", \"imagesSelect\":" + Arrays.toString(imagesSelect) +
                '}';
    }

}
