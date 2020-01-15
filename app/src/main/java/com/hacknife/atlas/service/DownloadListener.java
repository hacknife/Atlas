package com.hacknife.atlas.service;

public interface DownloadListener {
    void onProgress(int index, int len);

    void onComplete();
}
