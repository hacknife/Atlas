package com.hacknife.atlas.helper;

import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.bean.Images;
import com.hacknife.atlas.databinding.Image;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupHelper {
    public static final int LAST = -100;
    public static final int FIRST = -101;

    public static Elements parserAtlas(Document document) {
        return parser(document, AtlasResource.get().atlasSelect);
    }

    public static Elements parser(Document document, String[] selects) {
        int index = 0;
        String select = selects[index];
        int i = -1;
        if (select.contains("<")) {
            if (select.contains("last")) {
                i = LAST;
            } else if (select.contains("first")) {
                i = FIRST;
            } else {
                i = Integer.parseInt(select.substring(select.indexOf("<") + 1, select.indexOf(">")));
            }
            select = select.substring(0, select.indexOf("<"));
        }
        Elements elements = document.select(select);
        if (i != -1) {
            Element e;
            if (i == LAST) {
                e = elements.last();
            } else if (i == FIRST) {
                e = elements.first();
            } else {
                e = elements.get(i);
            }
            elements = new Elements();
            elements.add(e);
        }
        if (index + 1 >= selects.length)
            return elements;
        Elements eles = new Elements();
        for (Element element : elements) {
            eles.addAll(parser(element, selects, index + 1, selects.length));
        }
        return eles;
    }

    public static Elements parser(Element ele, String[] selects, int index, int end) {
        String select = selects[index];
        int i = -1;
        if (select.contains("<")) {
            if (select.contains("last")) {
                i = LAST;
            } else if (select.contains("first")) {
                i = FIRST;
            } else {
                i = Integer.parseInt(select.substring(select.indexOf("<") + 1, select.indexOf(">")));
            }
            select = select.substring(0, select.indexOf("<"));
        }
        Elements elements = ele.select(select);
        if (i != -1) {
            Element e;
            if (i == LAST) {
                e = elements.last();
            } else if (i == FIRST) {
                e = elements.first();
            } else {
                e = elements.get(i);
            }
            elements = new Elements();
            elements.add(e);
        }
        if (index + 1 >= end)
            return elements;
        Elements eles = new Elements();
        for (Element element : elements) {
            eles.addAll(parser(element, selects, index + 1, end));
        }
        return eles;
    }

    public static String atlasTitle(Element e) {
        return parserValue(e, AtlasResource.get().atlasTitle);
    }

    public static String atlasCover(Element e) {
        return parserValue(e, AtlasResource.get().atlasCover);
    }

    public static String atlasUrl(Element e) {
        return parserValue(e, AtlasResource.get().atlasUrl);
    }

    public static String parserValue(Element e, String[] select) {
        if (select.length == 1)
            return e.attr(select[0]);
        return parser(e, select, 0, select.length - 1).first().attr(select[select.length - 1]);
    }

    public static List<Atlas> atlasAtlas(Elements elements) {
        List<Atlas> atlases = new ArrayList<>(elements.size());
        for (Element element : elements) {
            atlases.add(new Atlas(atlasTitle(element), atlasCover(element), atlasUrl(element)));
        }
        return atlases;
    }

    public static Images parserImages(Document document) {
        return new Images(parserValue(document, AtlasResource.get().nextPageSelect), parserValues(document, AtlasResource.get().imagesSelect));
    }

    private static List<String> parserValues(Document document, String[] selects) {
        Elements elements = parser(document, selects, 0, selects.length - 1);
        List<String> list = new ArrayList<>(elements.size());
        for (Element element : elements) {
            String img = element.attr(selects[selects.length - 1]);
            list.add(img.startsWith("/") ? AtlasResource.get().host + img : img);
        }
        return list;
    }


    private static String parserValue(Document document, String[] selects) {
        return parser(document, selects, 0, selects.length - 1).first().attr(selects[selects.length - 1]);
    }
}
