package com.hacknife.atlas.helper;

import com.hacknife.atlas.bean.ImageSize;

import java.util.HashMap;
import java.util.Map;

public class ImageSizeContainer {
    private static ImageSizeContainer sImageSize;
    public Map<String, ImageSize> imageSize;

    public static Map<String, ImageSize> size() {
        if (sImageSize == null)
            synchronized (ImageSizeContainer.class) {
                if (sImageSize == null) {
                    sImageSize = new ImageSizeContainer();
                    sImageSize.imageSize = new HashMap<>();
                }
            }
        return sImageSize.imageSize;
    }
}
