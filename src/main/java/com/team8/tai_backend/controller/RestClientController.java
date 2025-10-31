package com.team8.tai_backend.controller;

import com.team8.tai_backend.dto.request.LLMRequest;
import com.team8.tai_backend.dto.request.TrendRssRequest;
import com.team8.tai_backend.service.RestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestClientController {
  private final RestClient restClient;
  private final RestClientService restService;

  @PostMapping("/rest/one")
  public LLMRequest fetchOne(@RequestBody TrendRssRequest request) {
    return restService.fetchOne(request);
  }

  @PostMapping("/rest/all")
  public List<LLMRequest> fetchAll(@RequestBody List<TrendRssRequest> request) {
    return restService.fetchAll(request);
  }
}
