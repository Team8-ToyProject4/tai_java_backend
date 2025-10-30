package com.team8.tai_backend.dto.response;

import com.team8.tai_backend.dto.request.LLMRequest;
import com.team8.tai_backend.entity.Trend;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * 트렌드 상세 조회 응답 DTO
 * <p>
 * 특정 트렌드의 상세 정보를 클라이언트에 전달하기 위한 응답 객체입니다.
 * 트렌드의 전체 컨텐츠, 참조 URL, 생성 시간 등 상세 정보를 포함합니다.
 *
 * @param id             트렌드 고유 ID
 * @param region         트렌드가 발생한 지역
 * @param rank           트렌드 순위
 * @param approx_traffic 대략적인 검색량
 * @param createdAt       트렌드 생성 일시 (ISO-8601 형식)
 * @param llmResult      트렌드 분석 결과 DTO (키워드/본문/설명/태그/카테고리/참조 URL)
 * @author chan
 * @see Trend
 * @since 1.0
 */

@Builder
public record TrendDetailResponse(
        Long id,
        String region,
        Long rank,
        String approx_traffic,
        LocalDateTime createdAt,
        LLMRequest llmResult
) {

    /**
     * Trend 엔티티를 TrendDetailResponse DTO로 변환합니다
     * <p>
     * 엔티티의 생성 시간을 ISO-8601 형식으로 포맷하고,
     * 저장된 참조 URL 목록을 그대로 응답에 포함합니다.
     *
     * @param trend 변환할 Trend 엔티티
     * @return 변환된 TrendDetailResponse 객체
     */
    public static TrendDetailResponse of(Trend trend) {

        LLMRequest llmRequest = LLMRequest.from(trend);

        return TrendDetailResponse.builder()
                .id(trend.getId())
                .region(trend.getRegion())
                .rank(trend.getRank())
                .approx_traffic(trend.getApprox_traffic())
                .llmResult(llmRequest)
                .createdAt(trend.getCreatedAt())
                .build();
    }
}
