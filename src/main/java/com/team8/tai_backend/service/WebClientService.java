package com.team8.tai_backend.service;

import com.team8.tai_backend.dto.response.TrendRssResponse;
import com.team8.tai_backend.dto.request.LLMRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Python 서버 통신 서비스
 * <p>
 * WebClient를 사용하여 Python LLM 서버와 비동기 HTTP 통신을 수행합니다.
 * 트렌드 키워드를 전송하고 LLM이 분석한 결과를 받아옵니다.
 *
 * @author chan
 * @see WebClient
 * @see LLMRequest
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class WebClientService {

    private final WebClient webClient;

    /**
     * Python 서버로 키워드를 전송하고 LLM 분석 결과를 받습니다
     * <p>
     * 지정된 키워드를 Python 서버의 /api/request 엔드포인트로 전송하여
     * LLM이 생성한 요약, 태그, 카테고리 등의 분석 결과를 비동기로 수신합니다.
     *
     * @return LLM 분석 결과를 담은 Mono 객체
     * @throws org.springframework.web.reactive.function.client.WebClientResponseException HTTP 요청 실패 시 (4xx, 5xx 에러)
     * @throws org.springframework.web.reactive.function.client.WebClientRequestException  네트워크 연결 실패 시
     */
    public Mono<LLMRequest> getAiComment() {

        log.debug("==== LLM에 응답 요청 시작 ====");

        TrendRssResponse request = new TrendRssResponse("예시 키워드");

        return webClient.post()
                .uri("/request")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(LLMRequest.class);
    }

//    public Flux<LLMResponse> sendToKeyword(TrendRssRequest rssRequest) {
//        return webClient.post()
//                .uri("/request")
//                .bodyValue(rssRequest)
//                .retrieve()
//                .bodyToFlux(LLMResponse.class);
//    }
}
