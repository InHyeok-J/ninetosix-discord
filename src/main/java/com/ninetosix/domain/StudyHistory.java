package com.ninetosix.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StudyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Member member;

    private Long channelId;

    private Long duration;

    private LocalDateTime start;

    private LocalDateTime end;


    public StudyHistory(Member member, LocalDateTime start, LocalDateTime end, Long channelId) {
        this.member = member;
        this.start = start;
        this.end = end;
        this.channelId = channelId;
    }

    @PrePersist
    private void calculateDuration() {
        if (start != null && end != null) {
            this.duration = Duration.between(start, end).getSeconds();
        }
    }
}
