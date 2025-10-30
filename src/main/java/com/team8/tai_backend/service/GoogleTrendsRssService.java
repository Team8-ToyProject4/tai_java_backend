package com.team8.tai_backend.service;

import com.team8.tai_backend.dto.response.TrendRssResponse;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jdom2.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.IntStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Google Trends Rss 파싱 Service
 *
 * @author daeun
 */
@Service
public class GoogleTrendsRssService {

    private static final String RSS_URL = "https://trends.google.co.kr/trending/rss?geo=KR";

    public List<TrendRssResponse> fetchLatestTrends() {
        try (XmlReader reader = new XmlReader(URI.create(RSS_URL).toURL())) {
            SyndFeed feed = new SyndFeedInput().build(reader);
            List<SyndEntry> entries = feed.getEntries();
            // 인덱스를 이용해 순위를 계산하며 DTO로 변환합니다. (index 0 → rank 1)
            return IntStream.range(0, entries.size())
                    .mapToObj(index -> toTrendRssResponse(entries.get(index), index + 1L))
                    .toList();
        } catch (IOException | FeedException e) {
            throw new IllegalStateException("구글 트렌드 RSS 파싱 실패", e);
        }
    }

    private TrendRssResponse toTrendRssResponse(SyndEntry entry, long rank) {
        String keyword = entry.getTitle();
        String approxTraffic = extractApproxTraffic(entry);

        return TrendRssResponse.builder()
                .keyword(keyword)
                .rank(rank)
                .approx_traffic(approxTraffic)
                .build();
    }

    private String extractApproxTraffic(SyndEntry entry) {
        if (entry == null) {
            return null;
        }

        if (entry.getForeignMarkup() != null) {
            for (Element element : entry.getForeignMarkup()) {
                if ("approx_traffic".equals(element.getName())) {
                    String value = element.getValue();
                    if (value != null && !value.isBlank()) {
                        return value.trim();
                    }
                }
            }
        }

        // 확장 마크업이 없을 경우 description HTML 안에서 approx_traffic 값을 찾아봅니다.
        if (entry.getDescription() != null && entry.getDescription().getValue() != null) {
            String description = entry.getDescription().getValue();
            Matcher matcher = APPROX_TRAFFIC_PATTERN.matcher(description);
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
        }

        return null;
    }

    private static final Pattern APPROX_TRAFFIC_PATTERN = Pattern.compile(
            "approx_traffic[^>]*>([^<]+)<",
            Pattern.CASE_INSENSITIVE
    );
}
