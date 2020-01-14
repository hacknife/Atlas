package com.hacknife.atlas;

import android.util.Log;

import com.google.gson.Gson;
import com.hacknife.atlas.bean.Arrays;
import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.helper.JsoupHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.onlite.OnLiteFactory;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

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
                new String[]{".content", "img", "src"}
        );
        Gson gson = new Gson();

        List<AtlasResource> list = new ArrayList<>();
//        list.add(AtlasResource.get());
        String content = Arrays.toString(AtlasResource.get().nextPageSelect);
        System.out.println(content);
        String[] a = Arrays.toList(content);
        System.out.println(Arrays.toString(a));
//        HttpClient.create(Api.class)
//                .url(Constant.ATLAS)
//                .subscribe(System.out::println);

    }


//            HttpClient.create(Api.class)
//            .url(AtlasResource.get().host + "/a/guochanmeinv/13737.html")
//            .map(Jsoup::parse)
//                .doOnNext(System.out::println)
//                .map(JsoupHelper::parserImages)
//                .subscribe(System.out::println);


//            HttpClient.create(Api.class)
//            .url(String.format(AtlasResource.get().page_url, 10))
//            .map(Jsoup::parse)
//                .map(JsoupHelper::parser)
//                .doOnNext(System.out::println)
//                .map(JsoupHelper::atlasAtlas)
//                .flatMap((Function<List<Atlas>, Observable<Atlas>>) elements -> Observable.fromArray(elements.toArray(new Atlas[]{})))
//            .subscribe(System.out::println);
}
