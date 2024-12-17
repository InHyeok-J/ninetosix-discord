package com.ninetosix.listener;

import com.ninetosix.domain.service.StudyService;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


@Slf4j
public class VoiceStatusListener extends ListenerAdapter {

    private final StudyService studyService;

    public VoiceStatusListener(StudyService studyService) {
        this.studyService = studyService;
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {

        Member member = event.getMember();
        AudioChannelUnion channelJoined = event.getChannelJoined();
        AudioChannelUnion channelLeft = event.getChannelLeft();

        if (studyService.isJoined(member.getId()) && channelLeft != null) {
            log.info("[Disconnect] " + member.getUser().getId() + " " + member.getEffectiveName() + " [CHANNEL] " + channelLeft.getName() + " " + channelLeft.getId());
            studyService.leave(member.getId());
            return;
        }

        if (channelJoined != null) {
            log.info("[Connect] " + member.getUser().getId() + " " + member.getEffectiveName() + " [CHANNEL] " + channelJoined.getName() + " " + channelJoined.getId());
            studyService.join(member.getId(), channelJoined.getIdLong());
        }
    }

}
