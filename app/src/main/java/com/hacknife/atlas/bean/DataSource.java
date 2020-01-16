package com.hacknife.atlas.bean;

import com.hacknife.atlas.R;
import com.hacknife.onlite.annotation.Column;
import com.hacknife.onlite.annotation.Table;

import static com.hacknife.atlas.bean.Arrays.toList;


@Table
public class DataSource {
    /**
     * 序列号
     */
    @Column("atlas_id")
    public Integer id;
    /**
     * 套图名称
     */
    @Column("atlas_name")
    public String name;
    /**
     * https://www.999mm.cn/
     */
    @Column("web_host")
    public String webHost;

    /**
     * https://www.999mm.cn/
     */
    @Column("image_host")
    public String imageHost;


    /**
     * https://www.999mm.cn/a/guochanmeinv/
     */
    @Column("atlas_atlas")
    public String atlas;
    /**
     * list_4_%d.html
     */
    @Column("atlas_page_url")
    public String page_url;
    /**
     * 套图选择器
     */

    @Column("atlas_sel")
    public String[] atlasSelect;
    /**
     * 套图标题
     */
    @Column("atlas_title")
    public String[] atlasTitle;
    /**
     * 套图封面
     */
    @Column("atlas_cover")
    public String[] atlasCover;
    /**
     * 套图URL
     */
    @Column("atlas_url")
    public String[] atlasUrl;
    /**
     * 下一页地址
     */
    @Column("atlas_next_page")
    public String[] nextPageSelect;
    /**
     * 套图图片
     */
    @Column("atlas_images")
    public String[] imagesSelect;

    /**
     * 选中
     */
    @Column("atlas_che")
    public Integer checked;

    @Column("web_headers")
    public String[] webHeaders;

    @Column("image_headers")
    public String[] imageHeaders;


    @Column("enable_cookie")
    public Integer enableCookie;

    @Column("share_cookie")
    public Integer shareCookie;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getWebHost() {
        return webHost;
    }

    public String getImageHost() {
        return imageHost;
    }

    public String getAtlas() {
        return atlas;
    }

    public String getPage_url() {
        return page_url;
    }

    public String getAtlasSelect() {
        return Arrays.toString(atlasSelect);
    }

    public String getAtlasTitle() {
        return Arrays.toString(atlasTitle);
    }

    public String getAtlasCover() {
        return Arrays.toString(atlasCover);
    }

    public String getAtlasUrl() {
        return Arrays.toString(atlasUrl);
    }

    public String getNextPageSelect() {
        return Arrays.toString(nextPageSelect);
    }

    public String getImagesSelect() {
        return Arrays.toString(imagesSelect);
    }

    public Integer getChecked() {
        return checked;
    }


    public String getWebHeaders() {
        return Arrays.toString(webHeaders);
    }

    public String getImageHeaders() {
        return Arrays.toString(imageHeaders);
    }

    public Integer getEnableCookie() {
        return enableCookie;
    }

    public Integer getShareCookie() {
        return shareCookie;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setWebHost(String webHost) {
        this.webHost = webHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public void setAtlas(String atlas) {
        this.atlas = atlas;
    }

    public void setPage_url(String page_url) {
        this.page_url = page_url;
    }

    public void setAtlasSelect(String atlasSelect) {
        this.atlasSelect = toList(atlasSelect);
    }

    public void setAtlasTitle(String atlasTitle) {
        this.atlasTitle = toList(atlasTitle);
    }

    public void setAtlasCover(String atlasCover) {
        this.atlasCover = toList(atlasCover);
    }

    public void setAtlasUrl(String atlasUrl) {
        this.atlasUrl = toList(atlasUrl);
    }

    public void setNextPageSelect(String nextPageSelect) {
        this.nextPageSelect = toList(nextPageSelect);
    }

    public void setImagesSelect(String imagesSelect) {
        this.imagesSelect = toList(imagesSelect);
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }


    public void setWebHeaders(String webHeaders) {
        this.webHeaders = toList(webHeaders);
    }

    public void setImageHeaders(String imageHeaders) {
        this.imageHeaders = toList(imageHeaders);
    }

    public void setEnableCookie(Integer enableCookie) {
        this.enableCookie = enableCookie;
    }

    public void setShareCookie(Integer shareCookie) {
        this.shareCookie = shareCookie;
    }

    public int getCheckedId() {
        if (checked())
            return R.drawable.ic_right;
        else
            return R.drawable.icon_null;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\'" + name + "\'" +
                ", \"webHost\":\'" + webHost + "\'" +
                ", \"imageHost\":\'" + imageHost + "\'" +
                ", \"atlas\":\'" + atlas + "\'" +
                ", \"page_url\":\'" + page_url + "\'" +
                ", \"atlasSelect\":" + java.util.Arrays.toString(atlasSelect) +
                ", \"atlasTitle\":" + java.util.Arrays.toString(atlasTitle) +
                ", \"atlasCover\":" + java.util.Arrays.toString(atlasCover) +
                ", \"atlasUrl\":" + java.util.Arrays.toString(atlasUrl) +
                ", \"nextPageSelect\":" + java.util.Arrays.toString(nextPageSelect) +
                ", \"imagesSelect\":" + java.util.Arrays.toString(imagesSelect) +
                ", \"checked\":" + checked +
                ", \"webHeaders\":" + java.util.Arrays.toString(webHeaders) +
                ", \"imageHeaders\":" + java.util.Arrays.toString(imageHeaders) +
                ", \"enableCookie\":" + enableCookie +
                ", \"shareCookie\":" + shareCookie +
                '}';
    }

    public boolean checked() {
        return checked != null && (checked == 1);
    }
}
