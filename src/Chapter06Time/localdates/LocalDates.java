package Chapter06Time.localdates;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

//本地时间  不涉及到时区，时区会在跨越边界时，时间可能会调整，晚或者早一个小时
public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("today: " + today);

        LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
        alonzosBirthday = LocalDate.of(1903, Month.JUNE, 14);
        System.out.println("alonzosBirthday :" + alonzosBirthday);

        LocalDate programmersDay = LocalDate.of(2014, 1, 1).plusDays(255);
        System.out.println("programmersDay : " + programmersDay);

        LocalDate independenceDay = LocalDate.of(2018, Month.JULY, 4);
        LocalDate christmas = LocalDate.of(2018, Month.DECEMBER, 25);
        System.out.println("Until christmas : " + independenceDay.until(christmas));
        System.out.println("Until christmas:" +
                independenceDay.until(christmas, ChronoUnit.DAYS));

        System.out.println(LocalDate.of(2016, 1, 31).plusMonths(1));
        System.out.println(LocalDate.of(2016, 3, 31).minusMonths(1));

        DayOfWeek startOfLastMillennium = LocalDate.of(1900, 1, 1).getDayOfWeek();
        System.out.println("startOfLastMillennium:" + startOfLastMillennium);
        System.out.println(startOfLastMillennium.getValue());
        System.out.println(DayOfWeek.SATURDAY.plus(3));


        //日期调整器 ， 诸如每个月的第一个星期二
        LocalDate firstTuesday = LocalDate.of(2020, 3, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        System.out.println(firstTuesday);

        TemporalAdjuster NEXT_WORKDAY= w -> {
            LocalDate result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() >= 6);
            return result;
        };
        LocalDate l = firstTuesday.with(NEXT_WORKDAY);
        System.out.println(l);
    }
}
