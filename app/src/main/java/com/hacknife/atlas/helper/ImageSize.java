package com.hacknife.atlas.helper;

import com.hacknife.atlas.bean.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageSize {
    private static ImageSize sImageSize;
    public Map<String, Image> imageSize;

    public static Map<String, Image> size() {
        if (sImageSize == null)
            synchronized (ImageSize.class) {
                if (sImageSize == null) {
                    sImageSize = new ImageSize();
                    sImageSize.imageSize = new HashMap<>();
                }
            }
        return sImageSize.imageSize;
    }
}
