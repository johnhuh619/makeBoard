package util;

import request.Request;

import java.util.HashMap;
import java.util.Map;

public class UrlParser {
    public static Request parseUrl(String url) {
        if (url == null || !url.startsWith("/")) {
            throw new IllegalArgumentException("Invalid URL");
        }
        /// example.com/page?query=123?ex=456
        /// ? 기준 2개로 분리
        /// example.com/page?query=123
        /// ?ex=456
        String[] parts = url.split("\\?",2);
        String path =  parts[0];
        Map<String, String> params = new HashMap<>();
        if(parts.length == 2) {
            String query = parts[1];
            String [] queryParams = query.split("&");
            for(String param : queryParams) {
                String[] keyValue = param.split("=");
                params.put(keyValue[0], keyValue[1]);
            }
        }
        return new Request(path, params);
    }
}
