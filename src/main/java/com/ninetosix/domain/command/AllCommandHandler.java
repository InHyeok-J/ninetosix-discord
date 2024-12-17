package com.ninetosix.domain.command;


import java.util.List;

public class AllCommandHandler {

    private final List<CommandHandler> commandHandlerList;

    public AllCommandHandler(List<CommandHandler> commandHandlerList) {
        this.commandHandlerList = commandHandlerList;
    }

    public void handle(CommandHandleRequest request) {
        for (CommandHandler commandHandler : commandHandlerList) {
            commandHandler.handle(request);
        }
    }
}
