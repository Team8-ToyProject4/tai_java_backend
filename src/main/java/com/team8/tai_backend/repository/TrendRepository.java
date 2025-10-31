package com.team8.tai_backend.repository;

import com.team8.tai_backend.entity.Trend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * java doc
 *
 * @author chan
 */
public interface TrendRepository extends JpaRepository<Trend, Long> {

    Optional<Trend> findById(Long id);
    List<Trend> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
