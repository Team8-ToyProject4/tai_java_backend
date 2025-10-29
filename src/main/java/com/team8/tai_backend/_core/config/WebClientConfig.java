package com.team8.tai_backend._core.config;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;


/**
 * WebClient 설정 클래스
 * <p>
 * 비동기 HTTP 클라이언트인 WebClient를 구성합니다.
 * Netty 기반의 Reactor HttpClient를 사용하여 타임아웃 및 연결 설정을 관리합니다.
 *
 * @author chan
 * @see WebClient
 * @see ReactorClientHttpConnector
 * @since 1.0
 */
@Configuration
public class WebClientConfig {

    /**
     * WebClient 빈을 생성합니다
     * <p>
     * 응답 타임아웃 5초, 연결 타임아웃 5초로 설정된 WebClient를 반환합니다.
     * 기본 URL은 로컬 API 핑 엔드포인트로 설정되어 있습니다.
     *
     * @return 설정이 적용된 WebClient 인스턴스
     */
    @Bean
    public WebClient webClient() {

        // TODO: 에러 발생시 어떤 결과가 나올까? 또 어떤 처리를 해줘야할까?
        // HttpClient 설정
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5)) // 응답 타임아웃
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000); // 연결 타임아웃

        return WebClient.builder()
                .baseUrl("http://127.0.0.1:8000/api") // TODO: yml 파일에 경로를 지정할 수 있다.
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    // 요청 로깅 필터


    // 응답 로깅 필터

}
