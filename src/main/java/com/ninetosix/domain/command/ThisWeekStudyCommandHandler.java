package com.ninetosix.domain.command;

import com.ninetosix.domain.dto.TotalStudyTimeResponse;
import com.ninetosix.domain.service.HistoryService;
import com.ninetosix.ui.TotalStudyEmbedMessageUtils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ThisWeekStudyCommandHandler implements CommandHandler {

    private final HistoryService historyService;
    private static final String MESSAGE = "이번주 공부 기록 순위입니다";

    public ThisWeekStudyCommandHandler(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public boolean matches(StudyCommand command) {
        return command.equals(StudyCommand.THIS_WEEK);
    }

    @Override
    public void process(CommandHandleRequest request) {
        LocalDateTime start = LocalDateTime.now().with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
        LocalDateTime end = LocalDateTime.now().with(DayOfWeek.SUNDAY).toLocalDate().atTime(LocalTime.MAX);

        List<TotalStudyTimeResponse> result = historyService.findStudySumWithinPeriod(start, end);
        SlashCommandInteractionEvent event = request.event();

        event.reply(MESSAGE)
                .addEmbeds(TotalStudyEmbedMessageUtils.getMessageEmbed(result))
                .queue();
    }
}
