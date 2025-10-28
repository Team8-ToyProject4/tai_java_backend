package com.team8.tai_backend.service;

import com.team8.tai_backend.dto.response.TrendDetailResponse;
import com.team8.tai_backend.dto.response.TrendResponse;
import com.team8.tai_backend.entity.Trend;
import com.team8.tai_backend.repository.TrendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * java doc
 *
 * @author chan
 */
@RequiredArgsConstructor
@Service
public class TrendService {

    // repository
    private final TrendRepository repository;

    // find_all
    public List<TrendResponse> getTrendBoard() {

        return repository.findAll().stream()
                .map(TrendResponse::of)
                .toList();
    }

    // findById
    public TrendDetailResponse getTrendDetail(Long id) {

        // repository에서 조회
        Trend trend = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Trend not found"));

        return TrendDetailResponse.of(trend);
    }

}
