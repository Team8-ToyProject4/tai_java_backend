package com.team8.tai_backend.controller;

import com.team8.tai_backend.dto.response.TrendRssResponse;
import com.team8.tai_backend.dto.request.LLMRequest;
import com.team8.tai_backend.service.WebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Python 서버 연동 테스트용 컨트롤러
 * <p>
 * WebClientService를 통한 Python 서버 통신을 테스트하기 위한 임시 컨트롤러입니다.
 * 개발/테스트 환경에서 Python API 호출을 확인할 수 있습니다.
 *
 * @author chan
 * @see WebClientService
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/test")
@RestController
public class PythonApiController {

    private final WebClientService webClientService;

    // WebClient가 아닌 RestClient를 사용하기로 했습니다. 주석처리합니다.
    /**
     * Python 서버로 키워드를 전송하고 LLM 분석 결과를 받습니다
     *
     * @param response 키워드가 포함된 요청 객체
     * @return LLM 분석 결과
     */
//    @PostMapping("/llm")
//    public Mono<LLMRequest> getTestResponse(@RequestBody TrendRssResponse response) {
//
//        log.debug("테스트 요청 수신 - keyword: {}", response.keyword());
//
//        return webClientService.getAiComment(response)
//
//                .doOnSuccess(request -> {
//                    if (request != null) {
//                        log.debug("컨트롤러 응답 성공 - keyword: {}", request.keyword());
//                        log.debug("응답 전체: {}", request);
//                    }
//                })
//
//                .doOnError(error -> {
//                    log.error("컨트롤러 에러 발생: {}", error.getMessage());
//                });
//    }

    /**
     * 헬스 체크 엔드포인트
     *
     * @return 서버 상태
     */
    @GetMapping("/health")
    public String health() {
        return "OK - Test Controller is running";
    }
}
