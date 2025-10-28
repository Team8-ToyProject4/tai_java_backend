package com.team8.tai_backend.dto.response;

import com.team8.tai_backend.entity.Trend;

import java.util.List;

/**
 * Trend 목록 응답 Dto
 *
 * @author chan
 */
public record TrendResponse(
        Long id, // id
        String region, // 지역
        Long rank, // 순위
        String keyword, // 키워드 (제목)
        String description, // 한 줄 설명
        String approx_traffic, // 검색량
        String category, // 카테고리 (tags보다 큰 개념)
        // tag <-> tags?
        List<String> tags // 관련 태그들
) {

    // Entity -> dto로 변환.
    public static TrendResponse of(Trend trend) {
        return new TrendResponse(
                trend.getId(),
                trend.getRegion(),
                trend.getRank(),
                trend.getKeyword(),
                trend.getDescription(),
                trend.getApprox_traffic(),
                trend.getCategory(),
                trend.getTags()
        );
    }
}
