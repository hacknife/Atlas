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
    public String webHost;
    /**
     * https://www.999mm.cn/
     */
    public String imageHost;
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
     * 网页请求头
     */
    public String[] webHeaders;
    /**
     * 图片请求头
     */
    public String[] imageHeaders;
    /**
     * 启用Cookie
     */
    private Integer enableCookie;
    /**
     * 共享图片和网页Cookie
     */
    private Integer shareCookie;
    /**
     * 资源网站过期
     */
    private Integer limit;

    public static DataSelector init(DataSource lite) {
        DataSelector resource = get();
        resource.name = lite.name;
        resource.webHost = lite.webHost;
        resource.imageHost = lite.imageHost;
        resource.atlas = lite.atlas;
        resource.page_url = lite.page_url;
        resource.atlasSelect = lite.atlasSelect;
        resource.atlasTitle = lite.atlasTitle;
        resource.atlasCover = lite.atlasCover;
        resource.atlasUrl = lite.atlasUrl;
        resource.nextPageSelect = lite.nextPageSelect;
        resource.imagesSelect = lite.imagesSelect;
        resource.webHeaders = lite.webHeaders;
        resource.imageHeaders = lite.imageHeaders;
        resource.enableCookie = lite.enableCookie;
        resource.shareCookie = lite.shareCookie;
        resource.limit = lite.limit;
        HttpClient.refresh();
        return resource;
    }

    public static DataSelector init(String webHost, String imageHost, String atlas, String page_url, String[] atlasSelect, String[] atlasTitle, String[] atlasCover, String[] atlasUrl, String[] nextPageSelect, String[] imagesSelect, String[] webHeaders, String[] imageHeaders, Integer enableCookie, Integer shareCookie, Integer limit) {
        DataSelector resource = get();
        resource.webHost = webHost;
        resource.imageHost = imageHost;
        resource.atlas = atlas;
        resource.page_url = page_url;
        resource.atlasSelect = atlasSelect;
        resource.atlasTitle = atlasTitle;
        resource.atlasCover = atlasCover;
        resource.atlasUrl = atlasUrl;
        resource.nextPageSelect = nextPageSelect;
        resource.imagesSelect = imagesSelect;
        resource.webHeaders = webHeaders;
        resource.imageHeaders = imageHeaders;
        resource.enableCookie = enableCookie;
        resource.shareCookie = shareCookie;
        resource.limit = limit;
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
                "\"name\":\'" + name + "\'" +
                ", \"webHost\":\'" + webHost + "\'" +
                ", \"imageHost\":\'" + imageHost + "\'" +
                ", \"atlas\":\'" + atlas + "\'" +
                ", \"page_url\":\'" + page_url + "\'" +
                ", \"atlasSelect\":" + Arrays.toString(atlasSelect) +
                ", \"atlasTitle\":" + Arrays.toString(atlasTitle) +
                ", \"atlasCover\":" + Arrays.toString(atlasCover) +
                ", \"atlasUrl\":" + Arrays.toString(atlasUrl) +
                ", \"nextPageSelect\":" + Arrays.toString(nextPageSelect) +
                ", \"imagesSelect\":" + Arrays.toString(imagesSelect) +
                ", \"webHeaders\":" + Arrays.toString(webHeaders) +
                ", \"imageHeaders\":" + Arrays.toString(imageHeaders) +
                ", \"enableCookie\":" + enableCookie +
                ", \"shareCookie\":" + shareCookie +
                ", \"limit\":" + limit +
                '}';
    }
}
