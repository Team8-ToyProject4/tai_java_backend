package com.team8.tai_backend.controller;

import com.team8.tai_backend.dto.response.TrendDetailResponse;
import com.team8.tai_backend.dto.response.TrendResponse;
import com.team8.tai_backend.service.TrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<TrendResponse> getTrendBoard() {

        return trendService.getTrendBoard();
    }

    // trend detail
    @GetMapping("/{id}")
    public TrendDetailResponse getTrendDetail(@PathVariable Long id) {

        return trendService.getTrendDetail(id);
    }

}
