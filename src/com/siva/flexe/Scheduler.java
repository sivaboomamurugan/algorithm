package com.siva.flexe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Scheduler {

    public static void main(String[] args) {

        testWithOneInvitee();

        System.out.println("End of first test case");

        testWithTwoInvitee();
        System.out.println("End of second test case");

        testWithThirdInvitee();

    }

    private static void testWithThirdInvitee() {

        List<MeetingTime> meetingTimes = getMeetingTimesforFirstInvitee();
        Invitee invitee1 = new Invitee(meetingTimes);

        List<MeetingTime> meetingTimes2 = getMeetingTimesForSecondInvitee();
        Invitee invitee2 = new Invitee(meetingTimes2);

        List<MeetingTime> meetingTimes3 = getMeetingTimesForThirdInvitee();
        Invitee invitee3 = new Invitee(meetingTimes3);

        List<Invitee> invitees = new ArrayList() {{
            add(invitee1);
            add(invitee2);
            add(invitee3);
        }};


        printFreeMeetingTimes(getMeetingTimes(invitees, getSearchTime()));

    }

    private static List<MeetingTime> getMeetingTimesForThirdInvitee() {
        return (List<MeetingTime>) new ArrayList() {{add(new MeetingTime(parseDate("2019-07-15 7:00"), parseDate("2019-07-15 8:30")));
            add(new MeetingTime(parseDate("2019-07-15 10:00"), parseDate("2019-07-15 10:30")));
            add(new MeetingTime(parseDate("2019-07-16 10:00"), parseDate("2019-07-16 15:30")));
        }};
    }

    private static List<MeetingTime> getMeetingTimesForSecondInvitee() {
        return (List<MeetingTime>) new ArrayList() {{add(new MeetingTime(parseDate("2019-07-15 6:00"), parseDate("2019-07-15 7:00")));
            add(new MeetingTime(parseDate("2019-07-15 9:00"), parseDate("2019-07-15 12:30")));
            add(new MeetingTime(parseDate("2019-07-15 14:00"), parseDate("2019-07-15 17:30")));
        }};
    }


    private static MeetingTime getSearchTime() {
        return new MeetingTime(parseDate("2019-07-15 5:00"), parseDate("2019-07-16 11:30"));
    }

    private static void printFreeMeetingTimes(List<MeetingTime> meetingTimes) {
        for (MeetingTime result : meetingTimes) {
            System.out.println(getDate(result.startDate) + " " + getDate(result.endDate));
        }
    }

    private static void testWithOneInvitee() {

        List<MeetingTime> meetingTimes = getMeetingTimesforFirstInvitee();
        Invitee invitee1 = new Invitee(meetingTimes);

        List<Invitee> invitees = new ArrayList<>();
        invitees.add(invitee1);

        printFreeMeetingTimes(getMeetingTimes(invitees, getSearchTime()));
    }

    private static List<MeetingTime> getMeetingTimesforFirstInvitee() {
        return (List<MeetingTime>) new ArrayList() {{add(new MeetingTime(parseDate("2019-07-15 7:00"), parseDate("2019-07-15 7:30")));
                add(new MeetingTime(parseDate("2019-07-15 10:00"), parseDate("2019-07-15 11:30")));
                add(new MeetingTime(parseDate("2019-07-15 13:00"), parseDate("2019-07-15 15:30")));
            }};
    }

    private static void testWithTwoInvitee() {

        List<MeetingTime> meetingTimes = getMeetingTimesforFirstInvitee();
        Invitee invitee1 = new Invitee(meetingTimes);

        List<MeetingTime> meetingTimes2 = getMeetingTimesForSecondInvitee();
        Invitee invitee2 = new Invitee(meetingTimes2);

        List<Invitee> invitees = new ArrayList() {{
           add(invitee1);
           add(invitee2);
        }};


        printFreeMeetingTimes(getMeetingTimes(invitees, getSearchTime()));
    }




    private static String getDate(Date date) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }


    private static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }


    private static List<MeetingTime> getMeetingTimes(List<Invitee> invitees, MeetingTime searchTime) {
        Invitee searchInvitee = new Invitee(searchTime);
        for(Invitee invitee : invitees) {
            filterMeetingTime(invitee, searchInvitee);
        }
        return searchInvitee.meetingTimes;

    }

    private static void filterMeetingTime(Invitee mergeFromInvitee, Invitee mergeToInvite) {

        Collections.sort(mergeToInvite.meetingTimes,new MeetinTimeComparator());
        Collections.sort(mergeFromInvitee.meetingTimes,new MeetinTimeComparator());

        Date maxEndDate = mergeToInvite.meetingTimes.get(mergeToInvite.meetingTimes.size()-1).endDate;
        for(int index = 0; index < mergeFromInvitee.meetingTimes.size(); index++) {
            MeetingTime currentMeetingTime = mergeFromInvitee.meetingTimes.get(index);

            Date currentStartDate = mergeFromInvitee.meetingTimes.get(index).startDate;

            if(currentStartDate.after(maxEndDate) || currentStartDate.equals(maxEndDate))
                break;

            removeMeetingTimeInCalendar(currentMeetingTime, mergeToInvite);
        }
    }

    public static void removeMeetingTimeInCalendar(MeetingTime time, Invitee invitee) {
        Iterator<MeetingTime> itr = invitee.meetingTimes.iterator();

        List<MeetingTime> tempListToAdd = new ArrayList<>();
        List<MeetingTime> tempListToRemove = new ArrayList<>();

        while(itr.hasNext()) {
            MeetingTime meetingTime = itr.next();

            if(meetingTime.startDate.before(time.startDate) && meetingTime.endDate.before(time.startDate) ||
                meetingTime.startDate.equals(time.startDate) && meetingTime.endDate.before(time.startDate) ||
                    meetingTime.startDate.before(time.startDate) && meetingTime.endDate.equals(time.startDate) ||
                    meetingTime.startDate.after(time.endDate) || meetingTime.startDate.equals(time.startDate))
                continue;

            if(meetingTime.startDate.after(time.startDate) &&  meetingTime.endDate.before(time.endDate) ||
                    meetingTime.startDate.equals(time.startDate) &&  meetingTime.endDate.equals(time.endDate))
                itr.remove();
            else if(meetingTime.startDate.before(time.startDate) && meetingTime.endDate.after(time.endDate)) {
                tempListToRemove.add(meetingTime);
                MeetingTime before = new MeetingTime(meetingTime.startDate, time.startDate);
                MeetingTime after = new MeetingTime(time.endDate, meetingTime.endDate);
                tempListToAdd.add(before);
                tempListToAdd.add(after);
            }
            else if(meetingTime.startDate.after(time.startDate) && meetingTime.endDate.after(time.endDate)) {
                tempListToRemove.add(meetingTime);
                MeetingTime after = new MeetingTime(time.endDate, meetingTime.endDate);
                tempListToAdd.add(after);
            }
            else if(meetingTime.startDate.before(time.startDate) && meetingTime.endDate.equals(time.endDate) ||
                    meetingTime.startDate.before(time.startDate) && meetingTime.endDate.before(time.endDate)) {
                tempListToRemove.add(meetingTime);
                MeetingTime after = new MeetingTime(meetingTime.startDate, time.startDate);
                tempListToAdd.add(after);
            }
        }

        invitee.meetingTimes.removeAll(tempListToRemove);
        invitee.meetingTimes.addAll(tempListToAdd);
    }




    //Not used
    private static boolean isTheInviteeFree(MeetingTime time, Invitee invitee) {

        for(MeetingTime meetingTime : invitee.meetingTimes) {
             if ((meetingTime.startDate.before(time.startDate) || meetingTime.startDate.equals(time.startDate))
                    && (meetingTime.endDate.after(time.endDate) || meetingTime.endDate.equals(time.endDate))) {
                 return true;
             }
        }
        return false;
    }


}
