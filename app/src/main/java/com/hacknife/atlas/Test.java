package com.hacknife.atlas;

public class Test {
    volatile static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + System.currentTimeMillis());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + System.currentTimeMillis());
                }
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock){
            lock.notify();
        }

    }


    //        DataSelector.init(
//                "https://www.999mm.cn/",
//                "https://www.999mm.cn/a/guochanmeinv/",
//                "list_4_%d.html",
//                new String[]{".main", ".boxs", ".img", "li", "a<0>"},
//                new String[]{"img", "alt"},
//                new String[]{"img", "src"},
//                new String[]{"a", "href"},
//                new String[]{"#pages", "a<last>", "href"},
//                new String[]{".content", "img", "src"},
//                new String[]{"User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400"},
//                1
//        );


//        HttpClient.create(Api.class)
//                .url(String.format(DataSelector.get().page_url, 1))
//                .map(Jsoup::parse)
//                .map(JsoupHelper::parserAtlas)
//                .doOnNext(System.out::println)
//                .map(JsoupHelper::atlasAtlas)
//    0122            .flatMap((Function<List<Atlas>, Observable<Atlas>>) elements -> Observable.fromArray(elements.toArray(new Atlas[]{})))
//                .subscribe(System.out::println);


//        HttpClient.create(Api.class)
//                .url("https://999mm.cn/a/guochanmeinv/13742.html")
//                .map(Jsoup::parse)
////                .doOnNext(System.out::println)
//                .map(JsoupHelper::parserImages)
//                .subscribe(System.out::println);


//            HttpClient.create(Api.class)
//            .url(String.format(DataSelector.get().page_url, 10))
//            .map(Jsoup::parse)
//                .map(JsoupHelper::parser)
//                .doOnNext(System.out::println)
//                .map(JsoupHelper::atlasAtlas)
//                .flatMap((Function<List<Atlas>, Observable<Atlas>>) elements -> Observable.fromArray(elements.toArray(new Atlas[]{})))
//            .subscribe(System.out::println);
}
