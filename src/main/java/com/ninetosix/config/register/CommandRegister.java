package com.ninetosix.config.register;

import com.ninetosix.domain.command.StudyCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandRegister {

    public static void registerCommands(Guild guild) {
        List<CommandData> commandsList = new ArrayList<>();
        for (StudyCommand command : StudyCommand.values()) {
            commandsList.add(Commands.slash(command.getSlash(), command.getDescription()));
        }
        guild.updateCommands().addCommands(
                commandsList
        ).queue();
    }


    // 인스턴스화를 막기 위한 private 생성자
    private CommandRegister() {
    }
}
