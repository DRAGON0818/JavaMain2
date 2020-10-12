package Chapter06Time.zonedtimes;

import java.time.*;
import java.util.Set;

public class ZonedTimes {
    public static void main(String[] args) {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1967, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));
        System.out.println("apollo11launch: " + apollo11launch);

        Instant instant = apollo11launch.toInstant();
        System.out.println("instant:" + instant);

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        System.out.println("zonedDateTime:" + zonedDateTime);


        ZonedDateTime skipped = ZonedDateTime.of(LocalDate.of(2013, 3, 31),
                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        System.out.println("skipped : " + skipped);

        ZonedDateTime ambiguous = ZonedDateTime.of(LocalDate.of(2013, 10, 27),
                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        ZonedDateTime anHourLater = ambiguous.plusHours(1);
        System.out.println("ambiguous : " + ambiguous);
        System.out.println("anHourLater:" + anHourLater);

        ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 31), LocalTime.of(14, 30)
                , ZoneId.of("America/Los_Angeles"));
        System.out.println("meeting : " + meeting);

        ZonedDateTime nextMeeting = meeting.plus(Duration.ofDays(7));
        System.out.println("nextMeeting: " + nextMeeting);

        nextMeeting = nextMeeting.plus(Period.ofDays(7));
        System.out.println("nextMeeting : "+nextMeeting);
/*
        // Java 使用了IANA数据库，里面保存着世界上所有的时区。
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        for (String s : availableZoneIds) {
            System.out.println("======= " + s);
        }
*/
    }
}
