package com.team8.tai_backend.service;

import com.team8.tai_backend.dto.request.LLMRequest;
import com.team8.tai_backend.dto.response.TrendDetailResponse;
import com.team8.tai_backend.dto.response.TrendResponse;
import com.team8.tai_backend.entity.Trend;
import com.team8.tai_backend.repository.TrendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * java doc
 *
 * @author chan
 */
@RequiredArgsConstructor
@Service
public class TrendService {

    // repository
    private final TrendRepository repository;

    // find_all
    public List<TrendResponse> getTrendBoard() {

        return repository.findAll().stream()
                .map(TrendResponse::of)
                .toList();
    }

    // findById
    public TrendDetailResponse getTrendDetail(Long id) {

        // repository에서 조회
        Trend trend = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Trend not found"));

        return TrendDetailResponse.of(trend);
    }

    @Transactional
    public TrendDetailResponse createTrend(LLMRequest request) {

        Trend trend = Trend.builder()
                .region("KR")
                .keyword(request.keyword())
                .description(request.description())
                .ai_description(request.description())
                .category(request.category())
                .content(request.content())
                .build();

        trend.updateTags(request.tags());
        trend.addReferences(request.refered());

        Trend savedTrend = repository.save(trend);

        return TrendDetailResponse.of(savedTrend);
    }

}
