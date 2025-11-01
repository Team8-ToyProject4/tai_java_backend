package com.team8.tai_backend._core.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestClientConfig {
  @Value("${python-url}")
  private String baseUrl;

  @Bean
  public RestClient restClient() {
    RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(30, TimeUnit.SECONDS)   // 연결
            .setResponseTimeout(5, TimeUnit.MINUTES)   // 읽기(응답 대기)
            .build();

    CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(requestConfig)
            .evictExpiredConnections()
            .evictIdleConnections(TimeValue.ofMinutes(5))
            .build();

    var factory = new HttpComponentsClientHttpRequestFactory(httpClient);

    return RestClient.builder()
            .baseUrl(baseUrl)
            .requestFactory(factory)
            .build();
  }
}