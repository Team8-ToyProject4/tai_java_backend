package com.team8.tai_backend.controller;

import com.team8.tai_backend.dto.request.LLMRequest;
import com.team8.tai_backend.dto.request.TrendRssRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequiredArgsConstructor
public class RestClientController {
  private final RestClient restClient;

  @GetMapping("/rest/one")
  public LLMRequest fetchOne(TrendRssRequest request) {
    LLMRequest result = restClient.post()
            .uri("/api/request")
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .body(LLMRequest.class);
    System.out.print(result);
    return result;
  }
}
