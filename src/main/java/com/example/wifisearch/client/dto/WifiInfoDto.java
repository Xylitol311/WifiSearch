package com.example.wifisearch.client.dto;


import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class WifiInfoDto {
    private String mgrNo; // 관리번호
    private String WRDOFC; // 자치구
    private String name; // 와이파이명
    private String streetAddress; // 도로명 주소
    private String detailAddress; // 상세 주소
    private String installFloor; // 설치 위치
    private String installType; // 설치 유형
    private String installMby; // 설치 기관
    private String svcEc; // 서비스 구분
    private String cmcwr; // 망종류
    private String cnstcYear; // 설치 년도
    private String inoutDoor; // 실내외 구분
    private String remars3; // 와이파이 접속환경
    private String latitude; //위도
    private String longitude; //경도
    private String workDttm; // 작업일자
    
    public static WifiInfoDto create(JsonObject jsonObject) { // 응답받은 json 파일을 WifiInfoDto로 반환하는 메소드
        return WifiInfoDto.builder().
                mgrNo(jsonObject.get("X_SWIFI_MGR_NO").getAsString()).
                WRDOFC(jsonObject.get("X_SWIFI_WRDOFC").getAsString()).
                name(jsonObject.get("X_SWIFI_MAIN_NM").getAsString()).
                streetAddress(jsonObject.get("X_SWIFI_ADRES1").getAsString()).
                detailAddress(jsonObject.get("X_SWIFI_ADRES2").getAsString()).
                installFloor(jsonObject.get("X_SWIFI_INSTL_FLOOR").getAsString()).
                installType(jsonObject.get("X_SWIFI_INSTL_TY").getAsString()).
                installMby(jsonObject.get("X_SWIFI_INSTL_MBY").getAsString()).
                svcEc(jsonObject.get("X_SWIFI_SVC_SE").getAsString()).
                cmcwr(jsonObject.get("X_SWIFI_CMCWR").getAsString()).
                cnstcYear(jsonObject.get("X_SWIFI_CNSTC_YEAR").getAsString()).
                inoutDoor(jsonObject.get("X_SWIFI_INOUT_DOOR").getAsString()).
                remars3(jsonObject.get("X_SWIFI_REMARS3").getAsString()).
                latitude(jsonObject.get("LAT").getAsString()).
                longitude(jsonObject.get("LNT").getAsString()).
                workDttm(jsonObject.get("WORK_DTTM").getAsString()).
                build();
    }
}
