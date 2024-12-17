package com.ninetosix.domain.service;

public interface StudyService {

    /**
     * 현재 공부중인지 확인한다
     * @author : Inhyeok-J
     * @param memberId : 디스코드에서 발급받은 회원 식별 Id
     * @return : 저장된 세션 정보가 있으면 true 없으면 false
     */
    boolean isJoined(String memberId);

    /**
     * 세션 정보를 저장함
     * @author : Inhyeok-J
     * @param memberId : 디스코드에서 발급받은 회원 식별 ID
     * @param channelId : 들어가고자 하는 채널 Id
     */
    void join(String memberId, Long channelId);

    /**
     * 세션 정보를 삭제함
     * @author : Inhyeok-J
     * @param memberId : 디스코드에서 발급받은 회원 식별 ID
     */
    void leave(String memberId);
}
