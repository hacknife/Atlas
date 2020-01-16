package com.hacknife.atlas.helper;

import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.DataSelector;
import com.hacknife.atlas.bean.Image;
import com.hacknife.atlas.bean.ImageCollection;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupHelper {
    public static final int LAST = -100;
    public static final int FIRST = -101;
    public static final int UNDEFINE = -102;
    public static final String LAST_STR = "last";
    public static final String FIRST_STR = "first";
    public static final String POSITION_FLAG_SUFFIX = ">";
    public static final String POSITION_FLAG_PREFIX = "<";

    public static Elements parserAtlas(Document document) {
        return parser(document, DataSelector.get().atlasSelect, 0, DataSelector.get().atlasSelect.length);
    }


    public static Elements parser(Element elementSource, String[] selects, int index, int length) {
        String select = selects[index];
        int position = UNDEFINE;
        if (select.contains(POSITION_FLAG_PREFIX)) {
            if (select.contains(LAST_STR)) {
                position = LAST;
            } else if (select.contains(FIRST_STR)) {
                position = FIRST;
            } else {
                position = Integer.parseInt(select.substring(select.indexOf(POSITION_FLAG_PREFIX) + 1, select.indexOf(POSITION_FLAG_SUFFIX)));
            }
            select = select.substring(0, select.indexOf(POSITION_FLAG_PREFIX));
        }
        Elements results = elementSource.select(select);
        if (position != UNDEFINE) {
            Element element = null;
            if (position == LAST) {
                element = results.last();
            } else if (position == FIRST) {
                element = results.first();
            } else if (position < results.size()) {
                element = results.get(position);
            }
            results = new Elements();
            if (element != null)
                results.add(element);
        }
        if (index + 1 >= length)
            return results;
        Elements elements = new Elements();
        for (Element element : results) {
            elements.addAll(parser(element, selects, index + 1, length));
        }
        return elements;
    }

    public static String parserValue(Element element, String[] select) {
        if (select == null || select.length == 0)
            return null;
        if (select.length == 1)
            return element.attr(select[0]);
        Element parser = parser(element, select, 0, select.length - 1).first();
        if (parser == null)
            return null;
        else
            return parser.attr(select[select.length - 1]);
    }

    private static List<String> parserValues(Element elementSource, String[] selects) {
        if (selects == null || selects.length == 0)
            return new ArrayList<>();
        else if (selects.length == 1) {
            List<String> list = new ArrayList<>();
            list.add(elementSource.attr(selects[selects.length - 1]));
            return list;
        }
        Elements elements = parser(elementSource, selects, 0, selects.length - 1);
        List<String> list = new ArrayList<>(elements.size());
        for (Element element : elements) {
            String img = element.attr(selects[selects.length - 1]);
            list.add(img);
        }
        return list;
    }


    public static String atlasTitle(Element e) {
        return parserValue(e, DataSelector.get().atlasTitle);
    }

    public static String atlasCover(Element e) {
        return parserValue(e, DataSelector.get().atlasCover);
    }

    public static String atlasUrl(Element e) {
        return parserValue(e, DataSelector.get().atlasUrl);
    }


    public static List<Atlas> atlasAtlas(Elements elements) {
        List<Atlas> atlases = new ArrayList<>(elements.size());
        for (Element element : elements) {
            atlases.add(new Atlas(atlasTitle(element), atlasCover(element), atlasUrl(element)));
        }
        return atlases;
    }

    public static ImageCollection parserImages(Element document) {
        List<String> imgs = parserValues(document, DataSelector.get().imagesSelect);
        List<Image> images = new ArrayList<>();
        for (String img : imgs) {
            images.add(new Image(0, 0, StringHelper.link(DataSelector.get().page_url, img)));
        }
        return new ImageCollection(parserValue(document, DataSelector.get().nextPageSelect), images);
    }


}
