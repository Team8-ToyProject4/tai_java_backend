package com.team8.tai_backend.entity;



import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * java doc
 *
 * @author chan
 */

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
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
    @ElementCollection
    @CollectionTable(name = "trend_tags", joinColumns = @JoinColumn(name = "trend_id"))
    @Column(name = "tag")
    @Builder.Default
    private List<String> tags = new ArrayList<>();

    // approx_traffic
    private String approx_traffic;

    // content
    private String content;

    @OneToMany(mappedBy = "trend", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Reference> references = new ArrayList<>();

    // startDate
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // 기존 태그 목록을 새로운 값으로 교체하며, null이 전달되면 아무 작업도 수행하지 않습니다.
    public void updateTags(List<String> tags) {
        this.tags.clear();
        if (tags == null) {
            return;
        }
        this.tags.addAll(tags);
    }

    // 단일 URL을 Reference 엔티티로 감싸 Trend에 연결합니다.
    public void addReference(String url) {
        this.references.add(Reference.of(this, url));
    }

    // 여러 URL을 순회하며 addReference를 호출해 일괄로 참조를 추가합니다.
    public void addReferences(List<String> urls) {
        if (urls == null) {
            return;
        }
        urls.forEach(this::addReference);
    }
}
