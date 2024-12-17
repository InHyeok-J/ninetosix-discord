package com.ninetosix.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "study")
public class Study {

    @Id
    private String memberId;

    private Long channelId;

    private LocalDateTime startTime;


    public static Study create(String memberId, Long channelId, LocalDateTime startTime) {
        return new Study(memberId, channelId, startTime);
    }

    public StudyHistory getHistory(Member member, LocalDateTime now) {
        return new StudyHistory(member, startTime, now, channelId);
    }

}
