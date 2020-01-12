package com.hacknife.atlas;

import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.HttpClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Test {
    public static void main(String[] args) {

        AtlasResource.init(
                "https://www.999mm.cn/",
                "https://www.999mm.cn/a/guochanmeinv/",
                "list_4_%d.html",
                new String[]{".main", ".boxs", ".img", "li", "a<0>"},
                new String[]{"img", "alt"},
                new String[]{"img", "src"},
                new String[]{"href"}
        );

        HttpClient.create(Api.class)
                .url(String.format(AtlasResource.get().page_url, 10))
                .map(Jsoup::parse)
                .map(JsoupHelper::parser)
//                .flatMap((Function<Elements, Observable<Element>>) elements -> Observable.fromArray(elements.toArray(new Element[]{})))
                .doOnNext(System.out::println)
                .map(JsoupHelper::atlasAtlas)
                .subscribe(System.out::println);
    }
}
