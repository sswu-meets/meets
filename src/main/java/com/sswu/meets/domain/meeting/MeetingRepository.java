package com.sswu.meets.domain.meeting;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    Meeting findByMeetingCode(String meetingCode);
}
