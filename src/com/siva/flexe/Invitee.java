package com.siva.flexe;

import java.util.ArrayList;
import java.util.List;

public class Invitee {

    String inviteeName;

    List<MeetingTime> meetingTimes;

    public Invitee(MeetingTime searchTime) {
        meetingTimes = new ArrayList<>();
        meetingTimes.add(searchTime);
    }

    public Invitee(List<MeetingTime> meetingTimes) {
        this.meetingTimes = meetingTimes;
    }
}
