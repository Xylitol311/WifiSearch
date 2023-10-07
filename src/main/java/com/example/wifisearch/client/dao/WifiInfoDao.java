package com.example.wifisearch.client.dao;

import com.example.wifisearch.client.dto.WifiInfoDto;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class WifiInfoDao {
    private static final String TABLE_NAME = "PUBLIC_WIFI_INFO"; // 연결할 테이블
    private static final int MAX_BATCH_CONTENT = 1000; //
    
    public void save(List<WifiInfoDto> publicWifiList) {
        Connection connection = ConnectionProvider.getConnection();
        // 입력받은 와이파이 객체 리스트를 stream을 이용하여 각각 createInsertQuery에 넣어서 리턴받은 String 값을 queries에 저장
        List<String> queries = publicWifiList.stream()
                .map(this::createInsertQuery)
                // createInsertQuery
                .collect(Collectors.toList());
        //work batch
        try (Statement statement = connection.createStatement()) {
            // Statement : 단일 ResultSet 개체를 반환하는 지정된 SQL 문을 실행
            // createStatement : 매개 변수화된 SQL 문을 데이터베이스로 보내기 위한 준비 상태 개체를 만듦
            for (int i = 0; i < queries.size(); i++) {
                String query = queries.get(i); // 위에서 생성한 쿼리문 리스트에서
                statement.addBatch(query); // 이 State 개체의 현재 SQL 명령 목록을 비웁니다
                if (i % MAX_BATCH_CONTENT == 0) {
                    statement.executeBatch(); // 이 문 개체를 만든 연결 개체를 검색
                }
            }
            statement.executeBatch();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            ConnectionProvider.close(connection);
        }
    }
    
    //dto 로 넘길까 고민
    private String createInsertQuery(WifiInfoDto wifiInfoDto) {
        return QueryBuilderFactory.createInsertQueryBuilder(TABLE_NAME)
                .addColumn("MGR_NO").value(wifiInfoDto.getMgrNo())
                .addColumn("WRDOFC").value(wifiInfoDto.getWRDOFC())
                .addColumn("NAME").value(wifiInfoDto.getName())
                .addColumn("STREET_ADDRESS").value(wifiInfoDto.getStreetAddress())
                .addColumn("DETAIL_ADDRESS").value(wifiInfoDto.getDetailAddress())
                .addColumn("INSTALL_FLOOR").value(wifiInfoDto.getInstallFloor())
                .addColumn("INSTALL_TYPE").value(wifiInfoDto.getInstallType())
                .addColumn("INSTALL_MBY").value(wifiInfoDto.getInstallMby())
                .addColumn("SVC_SE").value(wifiInfoDto.getSvcEc())
                .addColumn("CMCWR").value(wifiInfoDto.getCmcwr())
                .addColumn("CNSTC_YEAR").value(wifiInfoDto.getCnstcYear())
                .addColumn("INOUT_DOOR").value(wifiInfoDto.getInoutDoor())
                .addColumn("REMARS3").value(wifiInfoDto.getRemars3())
                .addColumn("LAT").value(wifiInfoDto.getLatitude())
                .addColumn("LNT").value(wifiInfoDto.getLongitude())
                .addColumn("WORK_DTTM").value(wifiInfoDto.getWorkDttm())
                .build();
    }
}
