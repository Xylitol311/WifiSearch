package com.example.wifisearch.client.dto;

// 공공 와이파이 API 사용시 필요한 요청 크기, api 키, base_uri를 이용해 최종 URI를 리턴하는 메소드를 포함한 클래스
public class PublicApiConfig {
    private static final int MAX_RESPONSE_COUNT = 999; //공공 wifi api에 요청할 수 있는 최대 개수
    private static final String API_KEY = "4d4c477171636b74313032724f6a4441";
    private static final String BASE_URI = "http://openapi.seoul.go.kr:8088";
    
    private PublicApiConfig() {
    
    }
    
    public static int getMaxResponseCount() {
        return MAX_RESPONSE_COUNT;
    }
    
    public static String createURI(String responseType, String url, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("시작값이 더 작아야 합니다");
        }
        if (start == end) {
            throw new IllegalArgumentException("최소 1건 이상의 요청 크기를 입력해주세요");
        }
        if (end - start > MAX_RESPONSE_COUNT) {
            throw new IllegalArgumentException("최대 요청 크기를 초과했습니다");
        }
        
        return BASE_URI + "/"
                + API_KEY + "/"
                + responseType + "/"
                + url + "/"
                + start + "/"
                + end;
    }
}
