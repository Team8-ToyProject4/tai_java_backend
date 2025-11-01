package com.team8.tai_backend._core.config;

import com.team8.tai_backend.service.NewTrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 스케줄링 작업을 관리하는 설정 클래스.
 * Spring의 Scheduled 애노테이션을 사용하여 주기적으로 실행되는 작업을 정의합니다.
 * 현재는 트렌드 데이터 수집 작업을 주기적으로 실행합니다.
 *
 * @author chan
 * @see org.springframework.scheduling.annotation.Scheduled
 * @see com.team8.tai_backend.service.NewTrendService
 */
@RequiredArgsConstructor
@Configuration
public class SchedularConfig {
    private final NewTrendService trendService;
    /**
     * 주기적으로 트렌드 데이터 수집 작업을 실행합니다.
     * 테스트 환경에서는 5분마다 실행되도록 설정되어 있습니다.
     * 프로덕션 환경에서는 매 시간 50분에 실행되도록 cron 표현식을 변경해야 합니다.
     */
    @Scheduled(cron = "0 */5 * * * *")
    public void executeEveryTimeAt50Minutes() {

        trendService.run();
    }
}
