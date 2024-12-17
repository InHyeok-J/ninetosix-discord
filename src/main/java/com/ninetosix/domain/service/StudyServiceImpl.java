package com.ninetosix.domain.service;

import com.ninetosix.domain.Member;
import com.ninetosix.domain.Study;
import com.ninetosix.domain.StudyHistory;
import com.ninetosix.domain.repository.MemberRepository;
import com.ninetosix.domain.repository.StudyHistoryRepository;
import com.ninetosix.domain.repository.StudyRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@Transactional
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final StudyHistoryRepository studyHistoryRepository;
    private final MemberRepository memberRepository;
    private final Long STUDY_ROOM_ID;


    public StudyServiceImpl(StudyRepository studyRepository, StudyHistoryRepository studyHistoryRepository, MemberRepository memberRepository, Long studyRoomId) {
        this.studyRepository = studyRepository;
        this.studyHistoryRepository = studyHistoryRepository;
        this.memberRepository = memberRepository;
        this.STUDY_ROOM_ID = studyRoomId;
    }

    @Override
    public boolean isJoined(String memberId) {
        return studyRepository.existsById(memberId);
    }

    @Override
    public void join(String memberId, Long channelId) {
        if(!channelId.equals(STUDY_ROOM_ID)) {
            // 참여시키지 않음.
            return;
        }

        Study study = Study.create(memberId, channelId, LocalDateTime.now());
        studyRepository.save(study);
    }

    @Override
    public void leave(String memberId) {
        Study study = studyRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("memberId에 대한 세션 정보를 찾을 수 없습니다."));
        Member member = memberRepository.findByDiscordId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("memberId에 대한 회원 정보를 찾을 수 없습니다."));
        StudyHistory history = study.getHistory(member, LocalDateTime.now());
        studyHistoryRepository.save(history);
        studyRepository.delete(study);
    }
}
