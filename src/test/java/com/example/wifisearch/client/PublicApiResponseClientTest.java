package com.example.wifisearch.client;

import com.example.wifisearch.client.dto.PublicApiConfig;
import com.example.wifisearch.client.dto.ResponseEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicApiResponseClientTest {
    
    @Test
    @DisplayName("실제 요청테스트) 공공api에 올바른 형식의 url을 get 요청을 했을 때 ResponseEntity에 파싱되어 반환되는 결과를 확인")
    void getPublicWifiList() {
        PublicApiResponseClient publicApiResponseClient = new PublicApiResponseClient();
        
        ResponseEntity responseEntity = publicApiResponseClient.getPublicWifiList(0, PublicApiConfig.getMaxResponseCount());
        
        String resultCode = responseEntity.getResultCode();
        Assertions.assertThat(resultCode).isEqualTo("INFO-000"); // 정상 작동 결과 코드를 받아오는지 확인
        Assertions.assertThat(responseEntity.getWifiInfos()).hasSize(PublicApiConfig.getMaxResponseCount()); // 받아온 사이즈가 최대 응답개수와 일치하는지 확인
    }
    
    @Test
    void getApiTotalCount() {
        PublicApiResponseClient publicApiResponseClient = new PublicApiResponseClient();
        
        int totalCount = publicApiResponseClient.getApiTotalCount();
        Assertions.assertThat(totalCount).isEqualTo(23415);
    }
}