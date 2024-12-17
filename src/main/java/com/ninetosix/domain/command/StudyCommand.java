package com.ninetosix.domain.command;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StudyCommand {
    YESTERDAY("공부통계-전날", "어제 스터디원들의 공부 순위를 확인해봐요"),
    THIS_WEEK("공부통계-이번주", "이번주 전날 스터디원들의 공부 순위를 확인해봐요");

    private final String slash;
    private final String description;

    StudyCommand(String slash, String description) {
        this.slash = slash;
        this.description = description;
    }


    public static StudyCommand valueOfBySlash(String slash) {
        return Arrays.stream(StudyCommand.values())
                .filter(command -> command.slash.equals(slash))
                .findAny()
                .orElse(null);
    }
}
