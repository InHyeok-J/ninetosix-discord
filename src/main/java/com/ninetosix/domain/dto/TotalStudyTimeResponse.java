package com.ninetosix.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TotalStudyTimeResponse {

    private Long totalStudyTime;
    private String name;
    private String discordId;

    public TotalStudyTimeResponse(String name, String discordId, long totalStudyTime) {
        this.totalStudyTime = totalStudyTime;
        this.name = name;
        this.discordId = discordId;
    }
}
