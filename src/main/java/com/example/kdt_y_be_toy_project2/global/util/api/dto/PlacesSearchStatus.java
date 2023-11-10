package com.example.kdt_y_be_toy_project2.global.util.api.dto;

public enum PlacesSearchStatus {
    OK, // 요청이 성공적으로 완료됨
    ZERO_RESULTS, // 검색에 대한 결과가 없음
    OVER_QUERY_LIMIT, // 할당량 초과
    REQUEST_DENIED, // 요청이 거부됨
    INVALID_REQUEST, // 잘못된 요청
    UNKNOWN_ERROR // 알 수 없는 오류
}
