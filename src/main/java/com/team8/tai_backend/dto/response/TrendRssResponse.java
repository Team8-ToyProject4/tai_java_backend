package com.team8.tai_backend.dto.response;

import com.team8.tai_backend.dto.request.TrendRssRequest;
import lombok.Builder;

/**
 * Google Trends Rss 파싱 DTO
 *
 * @author daeun
 */
@Builder
public record TrendRssResponse(
        String keyword,
        long rank,
        String approx_traffic
) {

    public TrendRssRequest toRequest() {
        return new TrendRssRequest(keyword);
    }
}
