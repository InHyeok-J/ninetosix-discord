package com.ninetosix.ui;

import com.ninetosix.domain.dto.TotalStudyTimeResponse;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.List;

public class TotalStudyEmbedMessageUtils {

    static StringBuilder sb = new StringBuilder();

    private static String formatSecondsToHoursAndMinutes(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;

        return String.format("%d시간 %d분", hours, minutes);
    }

    public static MessageEmbed getMessageEmbed(List<TotalStudyTimeResponse> result) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("공부 순위");
        eb.setColor(Color.GREEN);
        for (int i = 1; i <= result.size(); i++) {
            TotalStudyTimeResponse res = result.get(i - 1);
            sb.append(i).append("\u20E3 ").append(res.getName()).append("님 ");

            if(i==1){
                sb.append("\uD83C\uDFC5");
            }
            if(i==2){
                sb.append("\uD83E\uDD48");
            }
            if(i==3){
                sb.append("\uD83E\uDD49");
            }

            eb.addField(sb.toString(), formatSecondsToHoursAndMinutes(res.getTotalStudyTime()), false);
            sb.setLength(0);
        }
        return eb.build();
    }

    // 인스턴스화 방지 private
    private TotalStudyEmbedMessageUtils() {
    }
}
