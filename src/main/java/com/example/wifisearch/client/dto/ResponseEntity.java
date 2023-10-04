package com.example.wifisearch.client.dto;

import com.google.gson.annotations.JsonAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@JsonAdapter(WifiInfoDeserializer.class)
public class ResponseEntity {
    private Integer totalCount;
    private String resultCode;
    private List<WifiInfoDto> wifiInfos;
}
