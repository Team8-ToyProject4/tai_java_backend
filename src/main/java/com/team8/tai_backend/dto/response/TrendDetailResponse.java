package com.team8.tai_backend.dto.response;

import com.team8.tai_backend.entity.Reference;
import com.team8.tai_backend.entity.Trend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 트렌드 상세 조회 응답 DTO
 * <p>
 * 특정 트렌드의 상세 정보를 클라이언트에 전달하기 위한 응답 객체입니다.
 * 트렌드의 전체 컨텐츠, 참조 URL, 생성 시간 등 상세 정보를 포함합니다.
 *
 * @param id             트렌드 고유 ID
 * @param region         트렌드가 발생한 지역
 * @param rank           트렌드 순위
 * @param content        트렌드 상세 컨텐츠 (본문)
 * @param keyword        트렌드 키워드 (제목)
 * @param description    트렌드에 대한 한 줄 설명
 * @param approx_traffic 대략적인 검색량
 * @param category       트렌드 카테고리
 * @param tags           트렌드와 관련된 태그 목록
 * @param createAt       트렌드 생성 일시 (ISO-8601 형식)
 * @param references     참조 URL 목록
 * @author chan
 * @see Trend
 * @see Reference
 * @since 1.0
 */
public record TrendDetailResponse(
        Long id,
        String region,
        Long rank,
        String content,
        String keyword,
        String description,
        String approx_traffic,
        String category,
        List<String> tags,
        String createAt,
        List<String> references
) {

    /**
     * Trend 엔티티를 TrendDetailResponse DTO로 변환합니다
     * <p>
     * 엔티티의 생성 시간을 ISO-8601 형식으로 포맷하고,
     * Reference 엔티티 목록에서 URL만 추출하여 변환합니다.
     *
     * @param trend 변환할 Trend 엔티티
     * @return 변환된 TrendDetailResponse 객체
     */
    public static TrendDetailResponse of(Trend trend) {

        LocalDateTime dateTime = trend.getCreatedAt();

        return new TrendDetailResponse(
                trend.getId(),
                trend.getRegion(),
                trend.getRank(),
                trend.getContent(),
                trend.getKeyword(),
                trend.getDescription(),
                trend.getApprox_traffic(),
                trend.getCategory(),
                trend.getTags(),
                dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                trend.getReferences()
                        .stream()
                        .map(Reference::getUrl)
                        .toList()
        );
    }
}
