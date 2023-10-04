package com.example.wifisearch.client.dto;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WifiInfoDeserializer implements JsonDeserializer<ResponseEntity> { // JSON 파일 역직렬화
    
    @Override
    public ResponseEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) // gson custom deserialize
            throws JsonParseException {
        JsonObject responseBody = json.getAsJsonObject()
                .getAsJsonObject("TbPublicWifiInfo"); // 응답받은 JSON 파일에서 TbPublicWifiInfo 추출하여 JsonObject로 저장
        JsonArray row = responseBody.get("row").getAsJsonArray(); // resposeBody에서 "row"를 추출하여 와이파이 데이터 목록을 JsonArray 형태로 저장
        return new ResponseEntity(
                responseBody.get("list_total_count").getAsInt(),
                responseBody.get("RESULT").getAsJsonObject().get("CODE").getAsString(),
                jsonArrayToDto(row)
        );
    }
    
    private List<WifiInfoDto> jsonArrayToDto(JsonArray jsonArray) { // 저장된 JsonArray를 WifiInfoDto의 create 메서드를 이용해서 각 DTO 객체로 변환후 DTO List에 저장 후 리턴
        ArrayList<WifiInfoDto> wifiInfoDtos = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            wifiInfoDtos.add(WifiInfoDto.create(jsonElement.getAsJsonObject()));
        }
        return wifiInfoDtos;
    }
}
