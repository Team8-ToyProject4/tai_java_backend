package com.team8.tai_backend.dto.response;

import com.team8.tai_backend.entity.Trend;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 트렌드 목록 조회 응답 DTO
 * <p>
 * Google Trends 데이터의 기본 정보를 클라이언트에 전달하기 위한 응답 객체입니다.
 * 트렌드 목록 화면에 표시될 핵심 정보만 포함합니다.
 *
 * @param id             트렌드 고유 ID
 * @param region         트렌드가 발생한 지역
 * @param rank           트렌드 순위
 * @param keyword        트렌드 키워드 (제목)
 * @param description    트렌드에 대한 한 줄 설명
 * @param approx_traffic 대략적인 검색량
 * @param category       트렌드 카테고리 (태그보다 상위 개념)
 * @param tags           트렌드와 관련된 태그 목록
 * @author chan
 * @see Trend
 * @since 1.0
 */
public record TrendResponse(
        Long id,
        String region,
        Long rank,
        String keyword,
        String description,
        String approx_traffic,
        String category,
        List<String> tags,
        LocalDateTime createdAt
) {

    /**
     * Trend 엔티티를 TrendResponse DTO로 변환합니다
     *
     * @param trend 변환할 Trend 엔티티
     * @return 변환된 TrendResponse 객체
     */
    public static TrendResponse of(Trend trend) {
        return new TrendResponse(
                trend.getId(),
                trend.getRegion(),
                trend.getRank(),
                trend.getKeyword(),
                trend.getDescription(),
                trend.getApprox_traffic(),
                trend.getCategory(),
                trend.getTags(),
                trend.getCreatedAt()
        );
    }
}
