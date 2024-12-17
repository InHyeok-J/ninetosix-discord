package com.ninetosix.listener;

import com.ninetosix.domain.command.AllCommandHandler;
import com.ninetosix.domain.command.CommandHandleRequest;
import com.ninetosix.domain.command.StudyCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    private final AllCommandHandler allCommandHandler;

    public CommandListener(AllCommandHandler allCommandHandler) {
        this.allCommandHandler = allCommandHandler;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        String fullCommandName = event.getInteraction().getFullCommandName();

        StudyCommand studyCommand = StudyCommand.valueOfBySlash(fullCommandName);
        if (studyCommand == null) {
            event.reply("지원하지 않는 명령입니다").queue();
        }
        allCommandHandler.handle(new CommandHandleRequest(studyCommand, event));
    }
}
