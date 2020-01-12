package com.hacknife.atlas.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Cookie {
    private static Cookie cookie;
    private Map<String, String> map;


    public Cookie() {
        map = new HashMap<>();
    }

    public static Cookie get() {
        if (cookie == null) {
            synchronized (Cookie.class) {
                cookie = new Cookie();
            }
        }
        return cookie;
    }

    public static void putCookie(String content) {
        if (content == null) return;
        content = content.replaceAll(" ", "");
        if (content.length() == 0 || (!content.contains("="))) return;
        String[] cs = content.split(";");
        for (String cookie : cs) {
            if (!cookie.contains("=")) continue;
            get().map.put(cookie.split("=")[0], cookie.split("=")[1]);
        }
    }

    public static String cookie(String... names) {
        StringBuilder builder = new StringBuilder();
        for (String name : names) {
            builder.append(name).append("=").append(get().map.get(name)).append(";");
        }
        return builder.toString();
    }

    public static String cookie() {
        StringBuilder builder = new StringBuilder();
        Map<String, String> cookies = get().map;
        if (get().map.isEmpty()) return "";
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        }
        return builder.toString().substring(0, builder.length() - 1).replaceAll("\n", "");
    }


}
