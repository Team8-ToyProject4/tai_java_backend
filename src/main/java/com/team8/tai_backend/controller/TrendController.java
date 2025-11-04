package com.team8.tai_backend.controller;

import com.team8.tai_backend.dto.response.TrendDetailResponse;
import com.team8.tai_backend.dto.response.TrendResponse;
import com.team8.tai_backend.service.TrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 클라이언트 요청에 응답하는 컨트롤러 클래스.
 * javadoc
 *
 * @author chan
 * @link
 */
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/trend")
@RestController
public class TrendController {

    private final TrendService trendService;

    // trendList
    // 트렌드 목록 보여주기 (메인 화면)
    @GetMapping
    public List<TrendResponse> getTrendBoard(LocalDateTime targetDate) {

        return trendService.getTrendBoard(targetDate);
    }

    // trend detail
    @GetMapping("/{id}")
    public TrendDetailResponse getTrendDetail(@PathVariable Long id) {

        return trendService.getTrendDetail(id);
    }

    // 불필요하며 취약점이 될 수 있는 가능성이 있습니다. 주석 처리 합니다.
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public TrendDetailResponse createTrend(@RequestBody @Valid LLMRequest request) {
//
//        return trendService.createTrend(request);
//    }

}
