package com.hacknife.atlas.service;

import android.os.Binder;

import com.hacknife.atlas.bean.Atlas;

import java.util.List;


public class DownloadBinder extends Binder {
    DownloadListener downloadListener;

    public void registDownloadListener(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    public void unRegistDownloadListener() {
        this.downloadListener = null;
    }

    public void onProgress(int index, int len) {
        if (downloadListener != null) {
            downloadListener.onProgress(index, len);
        }
    }

    public void onComplete(Atlas atlas) {
        if (downloadListener != null) {
            downloadListener.onComplete(atlas);
        }
    }

    public List<Atlas> getAtlases() {
        return null;
    }
}
