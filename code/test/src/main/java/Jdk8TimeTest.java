import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @author bkunzh
 * @date 2020/10/21
 */
public class Jdk8TimeTest {
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
        System.out.println(localDate);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(formatter3.format(localDate));

        System.out.println(formatter3.format(LocalDate.of(2020, Month.SEPTEMBER, 2)));

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
