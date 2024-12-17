package com.ninetosix.domain.service;

import com.ninetosix.domain.dto.TotalStudyTimeResponse;
import com.ninetosix.domain.repository.StudyHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryServiceImpl implements HistoryService {

    private final StudyHistoryRepository studyHistoryRepository;

    @Override
    public List<TotalStudyTimeResponse> findStudySumWithinPeriod(java.time.LocalDateTime start, java.time.LocalDateTime end) {
        return studyHistoryRepository.findTotalStudyTime(start, end);
    }
}
