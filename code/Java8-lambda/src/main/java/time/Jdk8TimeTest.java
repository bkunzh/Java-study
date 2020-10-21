package time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

/**
 * @author bkunzh
 * @date 2020/10/21
 */
public class Jdk8TimeTest {

    @Test
    public void plusMinus() {
        System.out.println("增加/减少天数、小时");
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(2);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(df.format(localDate));

        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        dateTime = dateTime.minusDays(3).minusSeconds(1);
        System.out.println(formatter.format(dateTime));
        dateTime = dateTime.minusWeeks(1);
        System.out.println(formatter.format(dateTime));
        dateTime = dateTime.plusHours(12).withMinute(11);
        System.out.println(formatter.format(dateTime));
    }

    @Test
    public void between() {
        System.out.println("相差天数、小时等"); // toEpochDay until duration
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time2 = now.minusDays(1).minusHours(2).minusSeconds(20);
        Duration duration = Duration.between(time2, now);
        System.out.println("相差天数" + duration.toDays());
        System.out.println("相差小时" + duration.toHours());
        System.out.println("相差分钟" + duration.toMinutes());
        System.out.println("相差纳米" + duration.toNanos());

        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2020, Month.NOVEMBER, 25);
        System.out.println("方法二");
        System.out.println(date2.toEpochDay() - date1.toEpochDay());
        System.out.println(getDays(date1, date2));

        System.out.println("方法3");
        System.out.println(date1.until(date2, ChronoUnit.DAYS));

        System.out.println(time2.until(now, ChronoUnit.DAYS));
        System.out.println(time2.until(now, ChronoUnit.HOURS));
    }

    static int getDays(LocalDate date1, LocalDate date2) {
        LocalTime localTimeZero = LocalTime.of(0, 0, 0);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, localTimeZero);
        LocalDateTime dateTime2 = LocalDateTime.of(date2, localTimeZero);
        return (int) Duration.between(dateTime1, dateTime2).toDays();
    }

    @Test
    public void toDate() {
        System.out.println("转为Date、时间戳");

        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        System.out.println("Date = " + date);
        System.out.println("LocalDateTime = " + localDateTime);
        System.out.println(date.getTime());

        localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        date = Date.from(zdt.toInstant());
        System.out.println("LocalDateTime = " + localDateTime);
        System.out.println("Date = " + date);
    }

    @Test
    public void t1() {
        // DateTimeFormatter是线程安全的
        System.out.println("string to LocalDateTime");
        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        System.out.println(dateTime);

        System.out.println("LocalDateTime to string");
        LocalDateTime dateTime2 = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30);
        String formattedDateTime = dateTime2.format(formatter); // "1986-04-08 12:30"
        System.out.println(formattedDateTime);
        System.out.println("####");
    }

    @Test
    public void t2() {
        System.out.println("LocalDate");
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(formatter3.format(localDate));

        System.out.println(formatter3.format(LocalDate.of(2020, Month.SEPTEMBER, 2)));

        LocalDateTime localDateTime =LocalDateTime.of(localDate, LocalTime.now());
        System.out.println(localDateTime);

        System.out.println(localDateTime.toLocalDate());
        System.out.println(LocalDateTime.now().getDayOfWeek());
    }

    @Test
    public void t3() {
        System.out.println("LocalTime");
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("hh:mm:ss:SSS");
        System.out.println(formatter3.format(localTime));

    }

    @Test
    public void t4() {
        System.out.println("不同时区");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        LocalDateTime ldt3 = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
        System.out.println(ldt3);
        System.out.println(dtf2.format(ldt3));

        LocalDateTime ldt4 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt = ldt4.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);
        System.out.println(dtf2.format(ldt4));
        System.out.println(dtf2.format(zdt));

    }

    @Test
    public void t5() {
        System.out.println("所有可用时区");
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }
}
