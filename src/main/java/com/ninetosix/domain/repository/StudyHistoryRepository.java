package com.ninetosix.domain.repository;

import com.ninetosix.domain.StudyHistory;
import com.ninetosix.domain.dto.TotalStudyTimeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StudyHistoryRepository extends JpaRepository<StudyHistory, Integer> {

    @Query("SELECT new com.ninetosix.domain.dto.TotalStudyTimeResponse( " +
            "m.name, m.discordId, coalesce(SUM(sh.duration), 0)  ) " +
            "FROM Member m " +
            "LEFT JOIN  StudyHistory sh ON m.id = sh.member.id " +
            "WHERE sh.start > :start AND sh.end < :end OR sh.id IS NULL " +
            "GROUP BY m.id, m.name, m.discordId " +
            "ORDER BY SUM(sh.duration) DESC ")
    List<TotalStudyTimeResponse> findTotalStudyTime(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
