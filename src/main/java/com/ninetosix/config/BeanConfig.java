package com.ninetosix.config;

import com.ninetosix.domain.command.AllCommandHandler;
import com.ninetosix.domain.command.CommandHandler;
import com.ninetosix.domain.command.ThisWeekStudyCommandHandler;
import com.ninetosix.domain.command.YesterdayStudyCommandHandler;
import com.ninetosix.domain.service.HistoryService;
import com.ninetosix.domain.service.HistoryServiceImpl;
import com.ninetosix.domain.service.StudyService;
import com.ninetosix.domain.service.StudyServiceImpl;
import com.ninetosix.domain.repository.MemberRepository;
import com.ninetosix.domain.repository.StudyHistoryRepository;
import com.ninetosix.domain.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final StudyRepository studyRepository;
    private final StudyHistoryRepository studyHistoryRepository;
    private final MemberRepository memberRepository;

    @Value("${discord.room}")
    private Long STUDY_ROOM_ID;

    @Bean
    StudyService studyService() {
        return new StudyServiceImpl(studyRepository, studyHistoryRepository, memberRepository, STUDY_ROOM_ID);
    }

    @Bean
    HistoryService historyService(){
        return new HistoryServiceImpl(studyHistoryRepository);
    }

    @Bean
    CommandHandler yesterdayCommandHandler(){
        return new YesterdayStudyCommandHandler(historyService());
    }
    @Bean
    CommandHandler thisWeekCommandHandler(){
        return new ThisWeekStudyCommandHandler(historyService());
    }

    @Bean
    AllCommandHandler allCommandHandler(){
        List<CommandHandler> commandHandlers = new ArrayList<>();
        commandHandlers.add(yesterdayCommandHandler());
        commandHandlers.add(thisWeekCommandHandler());
        return new AllCommandHandler(commandHandlers);
    }
}
