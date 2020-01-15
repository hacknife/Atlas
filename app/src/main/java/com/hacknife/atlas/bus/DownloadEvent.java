package com.hacknife.atlas.bus;

import com.hacknife.atlas.bean.Atlas;

import java.util.List;

public class DownloadEvent {
    public List<Atlas> atlases;

    public DownloadEvent(List<Atlas> atlases) {
        this.atlases = atlases;
    }
}
