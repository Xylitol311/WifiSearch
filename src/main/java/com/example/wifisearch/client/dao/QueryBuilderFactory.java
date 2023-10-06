package com.example.wifisearch.client.dao;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilderFactory {
    private QueryBuilderFactory() {
    }
    
    public static InsertQueryBuilder createInsertQueryBuilder(String tableName) {
        String insertBase = String.format("insert into %s", tableName);
        return new InsertQueryBuilder(insertBase);
    }
    
    public static class InsertQueryBuilder {
        private final String query;
        private final List<String> columns;
        private final List<Object> values;
        
        public InsertQueryBuilder(String query) {
            this.query = query;
            this.columns = new ArrayList<>();
            this.values = new ArrayList<>();
        }
        
        public ValueBuilder addColumn(String column) {
            columns.add(column);
            return new ValueBuilder(this);
        }
        
        private InsertQueryBuilder addValue(String condition) {
            values.add(condition);
            return this;
        }
        
        public String build() { // 쿼리 합치는 부분
            if (values.size() != columns.size()) {
                throw new IllegalStateException("column과 values가 일치하지 않음");
            }
            StringBuilder queryBuilder = new StringBuilder(this.query);
            String columnsQuery = columns.stream()
                    .collect(Collectors.joining(",","(",")")); //컬럼 리스트를 String 한 줄로 변경 (1,2,3) 꼴
            queryBuilder.append(columnsQuery).append(" values(");
            
            for (int i = 0; i < values.size(); i++) {
                Object value = values.get(i);
                if (value instanceof Integer) { //value가 Integer 타입인지 확인해서 맞으면 실행
                    queryBuilder.append(values);
                    continue;
                }
                queryBuilder.append("\"")
                        .append(value)
                        .append("\"");
                if (i < values.size() - 1) {
                    queryBuilder.append(",");
                }
            }
            queryBuilder.append(")");
            return queryBuilder.toString();
        }
        @RequiredArgsConstructor
        static class ValueBuilder {
            private final InsertQueryBuilder insertQueryBuilder;
            
            public InsertQueryBuilder value(String condition) {
                return insertQueryBuilder.addValue(condition);
            }
        }
    }
}
