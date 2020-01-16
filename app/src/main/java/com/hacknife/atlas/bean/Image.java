package com.hacknife.atlas.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.hacknife.onlite.annotation.Ignore;
import com.hacknife.onlite.annotation.Table;
import com.hacknife.onlite.annotation.Unique;

@Table
public class Image implements Parcelable {
    Integer width;
    Integer height;
    @Unique
    String url;

    public Image() {
    }

    public Image(String url) {
        this.url = url;
    }

    public Image(Integer width, Integer height, String url) {
        this.width = width;
        this.height = height;
        this.url = url;
    }

    public Image(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    protected Image(Parcel in) {
        if (in.readByte() == 0) {
            width = null;
        } else {
            width = in.readInt();
        }
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readInt();
        }
        url = in.readString();
    }

    @Ignore
    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (obj instanceof Image)
            return ((Image) obj).url.equals(url);
        else
            return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (width == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(width);
        }
        if (height == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(height);
        }
        parcel.writeString(url);
    }

    @Override
    public String toString() {
        return "{" +
                "\"width\":" + width +
                ", \"height\":" + height +
                ", \"url\":\'" + url + "\'" +
                '}';
    }
}
