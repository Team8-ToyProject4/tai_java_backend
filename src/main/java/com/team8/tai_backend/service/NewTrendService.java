package com.team8.tai_backend.service;

import com.team8.tai_backend.dto.request.LLMRequest;
import com.team8.tai_backend.dto.response.TrendDetailResponse;
import com.team8.tai_backend.dto.response.TrendRssResponse;
import com.team8.tai_backend.entity.Trend;
import com.team8.tai_backend.repository.TrendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewTrendService {
  private final GoogleTrendsRssService rssService;
  private final RestClientService restService;

  private final TrendRepository trendRepository;

  public List<TrendDetailResponse> saveTrends(List<LLMRequest> response, List<TrendRssResponse> trends) {
    List<Trend> saveDatas = response.stream()
            .map(item -> Trend.builder()
                    .region("KR")
                    .keyword(item.keyword())
                    .rank((long) (response.indexOf(item) + 1))
                    .description(item.description())
                    .ai_description(item.description())
                    .category(item.category())
                    .content(item.content())
                    .approx_traffic(trends.get(response.indexOf(item)).approx_traffic())
                    .tags(item.tags())
                    .references(item.refered())
                    .build())
            .toList();

    return trendRepository.saveAll(saveDatas).stream()
            .map(TrendDetailResponse::of)
            .toList();
  }

  public List<TrendDetailResponse> run() {
    List<TrendRssResponse> trends = rssService.fetchLatestTrends();

    List<LLMRequest> res = restService.fetchAll(trends.stream()
            .map(TrendRssResponse::toRequest)
            .toList());

    return this.saveTrends(res, trends);
  }
}
