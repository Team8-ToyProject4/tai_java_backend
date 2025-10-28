package com.team8.tai_backend.dto.response;

import com.team8.tai_backend.entity.Reference;
import com.team8.tai_backend.entity.Trend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 트렌드 상세 정보 응답 Dto 객체
 *
 * @author chan
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

    // Entity -> dto로 변환
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
