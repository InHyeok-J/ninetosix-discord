package com.ninetosix.config;


import com.ninetosix.domain.command.AllCommandHandler;
import com.ninetosix.domain.service.StudyService;
import com.ninetosix.listener.CommandListener;
import com.ninetosix.listener.MessageListener;
import com.ninetosix.listener.VoiceStatusListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumSet;

@Configuration
@RequiredArgsConstructor
public class DiscordConfig {

    private final StudyService studyService;
    private final AllCommandHandler allCommandHandler;

    @Value("${discord.token}")
    private String DISCORD_TOKEN;

    @Bean
    public JDA jda() {
        EnumSet<GatewayIntent> settings = EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.MESSAGE_CONTENT);

        return JDABuilder.create(DISCORD_TOKEN, settings)
                .addEventListeners(voiceStatusListener())
                .addEventListeners(messageListener())
                .addEventListeners(commandListener())
                .build();
    }

    @Bean
    VoiceStatusListener voiceStatusListener() {
        return new VoiceStatusListener(studyService);
    }

    @Bean
    MessageListener messageListener() {
        return new MessageListener();
    }

    @Bean
    CommandListener commandListener() {
        return new CommandListener(allCommandHandler);
    }
}
