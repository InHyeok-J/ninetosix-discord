package com.ninetosix.domain.command;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public record CommandHandleRequest(StudyCommand studyCommand, SlashCommandInteractionEvent event) {

}
