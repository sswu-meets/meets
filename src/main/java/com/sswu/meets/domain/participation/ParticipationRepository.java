package com.sswu.meets.domain.participation;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findParticipationByUser(User user);
    List<Participation> findParticipationByMeeting(Meeting meeting);
    Participation findParticipationByUserAndMeeting(User user, Meeting meeting);
}
