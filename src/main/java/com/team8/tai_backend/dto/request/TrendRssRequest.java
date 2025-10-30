package com.team8.tai_backend.dto.request;

/**
 * Google Trends Rss 파이썬 요청 시 전달할 DTO
 *
 * @author daeun
 */
public record TrendRssRequest(
        String keyword
) {

}
