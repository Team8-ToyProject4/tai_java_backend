package com.team8.tai_backend.service;

import com.team8.tai_backend.dto.response.TrendRssResponse;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.List;

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
            return feed.getEntries()
                    .stream()
                    .map(this::toTrendRssResponse)
                    .toList();
        } catch (IOException | FeedException e) {
            throw new IllegalStateException("구글 트렌드 RSS 파싱 실패", e);
        }
    }

    private TrendRssResponse toTrendRssResponse(SyndEntry entry) {
        return new TrendRssResponse(
                entry.getTitle()
        );
    }
}
