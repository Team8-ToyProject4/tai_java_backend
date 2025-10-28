package com.team8.tai_backend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * java doc
 *
 * @author chan
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Trend {

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // region
    private String region;

    // rank
    private Long rank;

    // keyword
    private String keyword;

    // description (한줄평)
    private String description;

    // ai_summarize (요약)
    private String ai_description;

    // category
    private String category;

    // tags
    private List<String> tags;

    // approx_traffic
    private String approx_traffic;

    // content
    private String content;

    // reference
    private List<Reference> references;

    // startDate
    private LocalDateTime createdAt;

    @Builder
    public Trend(String region, Long rank, String keyword, String description, String ai_description, String category, List<String> tags, String approx_traffic, String content, List<Reference> references, LocalDateTime createdAt) {
        this.region = region;
        this.rank = rank;
        this.keyword = keyword;
        this.description = description;
        this.ai_description = ai_description;
        this.category = category;
        this.tags = tags;
        this.approx_traffic = approx_traffic;
        this.content = content;
        this.references = references;
        this.createdAt = createdAt;
    }
}
