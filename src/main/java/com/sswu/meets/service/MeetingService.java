package com.sswu.meets.service;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.meeting.MeetingRepository;
import com.sswu.meets.domain.participation.Participation;
import com.sswu.meets.domain.participation.ParticipationRepository;
import com.sswu.meets.domain.user.User;
import com.sswu.meets.domain.user.UserRepository;
import com.sswu.meets.dto.MeetingResponseDto;
import com.sswu.meets.dto.MeetingSaveRequestDto;
import com.sswu.meets.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;

    // 모임에 참여하고 있는 유저 조회
    @Transactional
    public List<UserResponseDto> getUserListOfMeeting(Long meeting_no) {
        Meeting participationMeeting = meetingRepository.getById(meeting_no);

        return participationRepository.findParticipationByMeeting(participationMeeting).stream()
                .map(p -> p.getUser())
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    // 모임 등록
    @Transactional
    public Long saveMeeting(Long user_no, MeetingSaveRequestDto requestDto) {
        String meetingCode = createMeetingCode();

        Long meeting_no = meetingRepository.save(requestDto.toEntity(meetingCode)).getMeeting_no();

        User participationUser = userRepository.getById(user_no);
        Meeting participationMeeting = meetingRepository.getById(meeting_no);

        Participation participation = Participation.builder()
                .meeting(participationMeeting)
                .user(participationUser)
                .build();
        participationRepository.save(participation);

        return meeting_no;
    }

    // 모임 참여
    @Transactional
    public MeetingResponseDto participateMeeting(Long user_no, String meetingCode) {
        User participationUser = userRepository.getById(user_no);
        Meeting participationMeeting = meetingRepository.findByMeetingCode(meetingCode);

        if (participationRepository.findParticipationByUserAndMeeting(participationUser, participationMeeting) == null) {
            Participation participation = Participation.builder()
                    .meeting(participationMeeting)
                    .user(participationUser)
                    .build();
            participationRepository.save(participation);
        }


        return new MeetingResponseDto(participationMeeting);
    }

    // 모임 나가기
    @Transactional
    public boolean leaveMeeting(Long user_no, String meetingCode) {
        User participationUser = userRepository.getById(user_no);
        Meeting participationMeeting = meetingRepository.findByMeetingCode(meetingCode);

        Participation participation = participationRepository.findParticipationByUserAndMeeting(participationUser, participationMeeting);

        try {
            participationRepository.delete(participation);
            return true;
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return false;
        }
    }

    // 모임 코드 생성
    private String createMeetingCode() {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();

        String meetingCode = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return meetingCode;
    }
}
