package com.hacknife.atlas.service;

import com.hacknife.atlas.bean.Atlas;

public interface DownloadListener {
    void onProgress(int index, int len);

    void onComplete(Atlas atlas);
}
