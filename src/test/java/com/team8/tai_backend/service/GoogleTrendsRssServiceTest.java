package com.team8.tai_backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team8.tai_backend.dto.response.TrendRssResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

class GoogleTrendsRssServiceTest {

    private final GoogleTrendsRssService service = new GoogleTrendsRssService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void printLatestTrendsAsJson() throws Exception {
        List<TrendRssResponse> responses = service.fetchLatestTrends();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responses);
        System.out.println(json);
    }
}
