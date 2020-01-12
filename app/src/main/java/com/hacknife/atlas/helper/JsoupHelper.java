package com.hacknife.atlas.helper;

import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.AtlasResource;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupHelper {
    public static Elements parser(Document document) {
        int index = 0;
        String select = AtlasResource.get().atlasSelect[index];
        int i = -1;
        if (select.contains("<")) {
            i = Integer.parseInt(select.substring(select.indexOf("<") + 1, select.indexOf(">")));
            select = select.substring(0, select.indexOf("<"));
        }
        Elements elements = document.select(select);
        if (i != -1) {
            Element e = elements.get(i);
            elements = new Elements();
            elements.add(e);
        }
        if (index + 1 >= AtlasResource.get().atlasSelect.length)
            return elements;
        Elements eles = new Elements();
        for (Element element : elements) {
            eles.addAll(parser(element, AtlasResource.get().atlasSelect, index + 1, AtlasResource.get().atlasSelect.length));
        }
        return eles;
    }

    public static Elements parser(Element ele, String[] selects, int index, int end) {
        String select = selects[index];
        int i = -1;
        if (select.contains("<")) {
            i = Integer.parseInt(select.substring(select.indexOf("<") + 1, select.indexOf(">")));
            select = select.substring(0, select.indexOf("<"));
        }
        Elements elements = ele.select(select);
        if (i != -1) {
            Element e = elements.get(i);
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
        return atlas(e, AtlasResource.get().atlasTitle);
    }

    public static String atlasCover(Element e) {
        return atlas(e, AtlasResource.get().atlasCover);
    }

    public static String atlasUrl(Element e) {
        return atlas(e, AtlasResource.get().atlasUrl);
    }

    public static String atlas(Element e, String[] select) {
        if (select.length == 1)
            return e.attr(select[0]);
        return parser(e, select, 0, select.length - 1).first().attr(select[select.length - 1]);

    }

    public static List<Atlas> atlasAtlas(Elements elements) {
//        String url = atlasUrl(element);
//        System.out.println(url);
//
//
//        String title = atlasTitle(element);
//        System.out.println(title);
//
//
//        String cover = atlasCover(element);
//        System.out.println(cover);
        List<Atlas> atlases=new ArrayList<>(elements.size());
        for (Element element : elements) {
            atlases.add(new Atlas(atlasTitle(element), atlasCover(element), atlasUrl(element)));
        }
        return atlases;
    }
}
