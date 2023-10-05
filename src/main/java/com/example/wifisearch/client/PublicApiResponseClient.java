package com.example.wifisearch.client;

import com.example.wifisearch.client.dto.PublicApiConfig;
import com.example.wifisearch.client.dto.ResponseEntity;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static com.example.wifisearch.client.dto.PublicApiConfig.createURI;

public class PublicApiResponseClient {
    public ResponseEntity getPublicWifiList(int start, int end) { // 외부에서 호출하는 메소드
        return getResponseEntity(start, end); // 내부에서 getResponseEntity() 메소드를 호출하여 ResponseEntity 반
    }
    
    private ResponseEntity getResponseEntity(int start, int end) {
        // okhttp를 이용하여 request 요청 및 reponse 리턴
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request build = new Request.Builder().url(createURI(start, end))
                    .get()
                    .build();
            Response execute = okHttpClient.newCall(build).execute();
            if (execute.isSuccessful()) {
                Gson gson = new Gson();
                return gson.fromJson(execute.body().string(), ResponseEntity.class);
            }
            throw new IllegalArgumentException("응답실패");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private String createURI(int start, int end) {
        return PublicApiConfig.createURI("json", "TbPublicWifiInfo", start, end);
    }
}
