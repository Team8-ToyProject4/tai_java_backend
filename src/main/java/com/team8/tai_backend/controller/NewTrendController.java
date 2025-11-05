package com.team8.tai_backend.controller;

import com.team8.tai_backend.dto.response.TrendDetailResponse;
import com.team8.tai_backend.service.NewTrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewTrendController {
  private final NewTrendService newTrendService;

  @Value("${request-pw}")
  private String pw;

  @GetMapping("/entire/test")
  public List<TrendDetailResponse> testController(@RequestParam String password) {
    if (password.equals(pw))
      return newTrendService.run();
    return List.of();
  }
}
