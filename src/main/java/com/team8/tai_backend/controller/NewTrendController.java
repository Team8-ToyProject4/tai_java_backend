package com.team8.tai_backend.controller;

import com.team8.tai_backend.dto.response.TrendDetailResponse;
import com.team8.tai_backend.service.NewTrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewTrendController {
  private final NewTrendService newTrendService;

  @GetMapping("/entire/test")
  public List<TrendDetailResponse> testController() {
    return newTrendService.run();
  }
}
