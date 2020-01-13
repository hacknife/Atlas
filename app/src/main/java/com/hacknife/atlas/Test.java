package com.hacknife.atlas;

import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.HttpClient;

import org.jsoup.Jsoup;

public class Test {
    public static void main(String[] args) {

        AtlasResource.init(
                "https://www.999mm.cn/",
                "https://www.999mm.cn/a/guochanmeinv/",
                "list_4_%d.html",
                new String[]{".main", ".boxs", ".img", "li", "a<0>"},
                new String[]{"img", "alt"},
                new String[]{"img", "src"},
                new String[]{"href"},
                new String[]{"#pages", "a<last>", "href"},
                new String[]{".content","img","src"}
        );

        HttpClient.create(Api.class)
                .url(AtlasResource.get().host + "/a/guochanmeinv/13737.html")
                .map(Jsoup::parse)
                .doOnNext(System.out::println)
                .map(JsoupHelper::parserImages)
                .subscribe(System.out::println);
    }

//            HttpClient.create(Api.class)
//            .url(String.format(AtlasResource.get().page_url, 10))
//            .map(Jsoup::parse)
//                .map(JsoupHelper::parser)
//                .doOnNext(System.out::println)
//                .map(JsoupHelper::atlasAtlas)
//                .flatMap((Function<List<Atlas>, Observable<Atlas>>) elements -> Observable.fromArray(elements.toArray(new Atlas[]{})))
//            .subscribe(System.out::println);
}
