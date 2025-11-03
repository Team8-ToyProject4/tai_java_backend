package com.team8.tai_backend._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Configuration
public class TimeProviderConfig {
  @Bean
  public DateTimeProvider koreaDateTimeProvider() {
    return () -> Optional.of(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
  }
}
