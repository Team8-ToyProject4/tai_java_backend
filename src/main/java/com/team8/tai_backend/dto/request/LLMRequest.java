package com.team8.tai_backend.dto.request;

import com.team8.tai_backend.entity.Trend;

import java.util.List;

/**
 * LLM 분석 결과 요청 DTO
 * <p>
 * LLM(Large Language Model)이 트렌드 데이터를 분석한 결과를 요청하는 객체입니다.
 * 키워드 추출, 설명 생성, 태그 분류 등 AI 분석 결과를 포함합니다.
 *
 * @param keyword     LLM이 추출한 핵심 키워드
 * @param description LLM이 생성한 트렌드 설명
 * @param content     LLM이 생성한 트렌드 상세 컨텐츠
 * @param tags        LLM이 분류한 관련 태그 목록
 * @param category    LLM이 분류한 트렌드 카테고리
 * @param refered     참조된 뉴스/기사 URL 목록
 * @author chan
 * @since 1.0
 */
public record LLMRequest(
        String keyword,
        String description,
        String content,
        List<String> tags,
        String category,
        List<String> refered
) {

    public static LLMRequest from(Trend trend) {

        return new LLMRequest(
                trend.getKeyword(),
                trend.getDescription(),
                trend.getContent(),
                trend.getTags(),
                trend.getCategory(),
                trend.getReferences()
        );
    }
}
