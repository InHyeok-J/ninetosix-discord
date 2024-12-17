package com.ninetosix.domain.command;

import com.ninetosix.domain.dto.TotalStudyTimeResponse;
import com.ninetosix.domain.service.HistoryService;
import com.ninetosix.ui.TotalStudyEmbedMessageUtils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.LocalDateTime;
import java.util.List;

public class YesterdayStudyCommandHandler implements CommandHandler {

    private final HistoryService historyService;
    private static final String MESSAGE = "어제 공부 기록 순위입니다.";

    public YesterdayStudyCommandHandler(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public boolean matches(StudyCommand command) {
        return command.equals(StudyCommand.YESTERDAY);
    }

    @Override
    public void process(CommandHandleRequest request) {
        LocalDateTime start = LocalDateTime.now().minusDays(1).toLocalDate().atStartOfDay();
        LocalDateTime end = LocalDateTime.now().toLocalDate().atStartOfDay();

        List<TotalStudyTimeResponse> result = historyService.findStudySumWithinPeriod(start, end);

        SlashCommandInteractionEvent event = request.event();

        event.reply(MESSAGE)
                .addEmbeds(TotalStudyEmbedMessageUtils.getMessageEmbed(result))
                .queue();
    }



}
