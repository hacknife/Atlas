package com.hacknife.atlas.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.hacknife.atlas.helper.StringHelper;
import com.hacknife.onlite.annotation.Ignore;
import com.hacknife.onlite.annotation.Table;

@Table
public class Atlas implements Parcelable {

    String title;
    String cover_url;
    String image_url;
    Integer cached;
    @Ignore
    int size;
    @Ignore
    int current;

    public Atlas(String title, String cover_url, String image_url) {
        this.title = title;
        this.cover_url = cover_url;
        this.image_url = image_url;
    }

    public Atlas() {
    }


    protected Atlas(Parcel in) {
        title = in.readString();
        cover_url = in.readString();
        image_url = in.readString();
        if (in.readByte() == 0) {
            cached = null;
        } else {
            cached = in.readInt();
        }
        size = in.readInt();
        current = in.readInt();
    }

    @Ignore
    public static final Creator<Atlas> CREATOR = new Creator<Atlas>() {
        @Override
        public Atlas createFromParcel(Parcel in) {
            return new Atlas(in);
        }

        @Override
        public Atlas[] newArray(int size) {
            return new Atlas[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getCached() {
        return cached;
    }

    public void setCached(Integer cached) {
        this.cached = cached;
    }

    public String getCover() {
        return StringHelper.link(DataSelector.get().page_url, cover_url);
    }


    public String getProgress() {
        if (size != 0)
            return String.format("%d/%d", current, size);
        return "0/1+";
    }

    public String getUrl() {
        return StringHelper.link(DataSelector.get().page_url, image_url);
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
                ", \"cover_url\":\'" + cover_url + "\'" +
                ", \"image_url\":\'" + image_url + "\'" +
                ", \"cached\":" + cached +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(cover_url);
        parcel.writeString(image_url);
        if (cached == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cached);
        }
        parcel.writeInt(size);
        parcel.writeInt(current);
    }
}
