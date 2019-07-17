package com.siva.flexe;

import java.util.Comparator;

public class MeetinTimeComparator implements Comparator<MeetingTime> {
    public int compare(MeetingTime from, MeetingTime to)
    {
        if (from.endDate.before(to.endDate))
            return -1;
        else if(from.endDate.after(to.endDate))
            return 1;
        else
            return 0;
    }
}
