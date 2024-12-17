package com.ninetosix.listener;

import com.ninetosix.config.register.CommandRegister;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Slf4j
public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User user = event.getAuthor();
        if (user.isBot()) {
            return;
        }
        Member member = event.getMember();
        if (event.getMessage().getContentDisplay().equals("커맨드최신화")) {

            if(!member.getNickname().equals("조인혁")) {
                log.warn("권한이 없습니다 " + member.getNickname());
                return;
            }

            Guild guild = event.getGuild();
            CommandRegister.registerCommands(guild);
        }
    }
}
