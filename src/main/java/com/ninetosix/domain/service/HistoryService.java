package com.ninetosix.domain.service;

import com.ninetosix.domain.dto.TotalStudyTimeResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryService {

    /**
     * 인자로 넘긴 기간사이의 기록들을 종합해 리턴함
     * @param start : 시작 시간
     * @param end : 종료 시간
     * @return : Total 시간 결과들
     */
    List<TotalStudyTimeResponse> findStudySumWithinPeriod(LocalDateTime start, LocalDateTime end);
}
