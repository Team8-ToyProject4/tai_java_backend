package com.team8.tai_backend.service;

import com.team8.tai_backend.dto.request.LLMRequest;
import com.team8.tai_backend.dto.response.TrendDetailResponse;
import com.team8.tai_backend.dto.response.TrendResponse;
import com.team8.tai_backend.dto.response.TrendRssResponse;
import com.team8.tai_backend.entity.Trend;
import com.team8.tai_backend.repository.TrendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final GoogleTrendsRssService googleTrendsRssService;

    // find_all
    public List<TrendResponse> getTrendBoard() {
//        LocalDate targetDate = LocalDate.of(2025, 10, 31); // 아마 테스트용이었겠죠?? 주석 처리 하겠습니다.
        LocalDate targetDate = LocalDate.now();
        LocalDateTime startOfDay = targetDate.atStartOfDay();
        LocalDateTime endOfDay = targetDate.atTime(LocalTime.MAX);

        return repository.findAllByCreatedAtBetween(startOfDay, endOfDay).stream()
                .map(TrendResponse::of)
                .toList();
    }

    // findById
    public TrendDetailResponse getTrendDetail(Long id) {

        // repository에서 조회
        Trend trend = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Trend not found"));

        return TrendDetailResponse.of(trend);
    }

    // 너무 많은 책임을 갖고 있어 이전 커밋에서 분산시켰습니다. 주석 처리 합니다.
//    @Transactional
//    public TrendDetailResponse createTrend(LLMRequest request) {
//
//        TrendRssResponse rssData = googleTrendsRssService.fetchLatestTrends()
//                .stream()
//                .filter(rss -> rss.keyword().equalsIgnoreCase(request.keyword()))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("RSS에서 해당 키워드를 찾을 수 없습니다: " + request.keyword()));
//
//        Long rank = rssData.rank();
//        String approxTraffic = rssData.approx_traffic();
//
//        Trend trend = Trend.builder()
//                .region("KR")
//                .keyword(request.keyword())
//                .rank(rank)
//                .description(request.description())
//                .ai_description(request.description())
//                .category(request.category())
//                .content(request.content())
//                .approx_traffic(approxTraffic)
//                .build();
//
//        trend.updateTags(request.tags());
//        trend.updateReferences(request.refered());
//
//        Trend savedTrend = repository.save(trend);
//
//        return TrendDetailResponse.of(savedTrend);
//    }

}
