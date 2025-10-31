package com.team8.tai_backend.service;

import com.team8.tai_backend.dto.request.LLMRequest;
import com.team8.tai_backend.dto.request.TrendRssRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class RestClientService {
  private final RestClient restClient;

  /// 한 개 요청
  public LLMRequest fetchOne(TrendRssRequest request) {
    LLMRequest result = restClient.post()
            .uri("/api/request")
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .body(LLMRequest.class);
    System.out.print("요청 완료: " + result.keyword() + "\n");
    return result;
  }

  /// 여러 개 요청
  public List<LLMRequest> fetchAll(List<TrendRssRequest> requests) {
    // 요청별로 병렬 호출을 수행하기 위해 가상 스레드 기반 executor 사용
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      // Future 리스트로 각 요청의 순서를 보존하기 위해 인덱스를 함께 저장
      var futures = IntStream.range(0, requests.size())
              .mapToObj(i -> CompletableFuture.supplyAsync(
                      () -> Map.entry(i, fetchOne(requests.get(i))), executor))
              .toList();

      // 비동기 결과를 기다린 후 입력 순서대로 다시 정렬하여 반환
      return futures.stream()
              .map(CompletableFuture::join)
              .sorted(Comparator.comparingInt(Map.Entry::getKey))
              .map(Map.Entry::getValue)
              .toList();
    }
  }
}
