package com.ninetosix.domain.command;

public interface CommandHandler {

    boolean matches(StudyCommand command);

    void process(CommandHandleRequest request);

    default void handle(CommandHandleRequest request) {
        if (matches(request.studyCommand())) {
            process(request);
        }
    }

}
