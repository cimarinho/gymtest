package br.com.gymtest.gymtest.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeUtilsTest  {

    @Test
    public void second(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        String time = "00:01:30";
        LocalTime time1 = LocalTime.parse(time, dateTimeFormatter);
        time = "00:02:00";
        LocalTime time2 = LocalTime.parse(time, dateTimeFormatter);
        Duration duration = Duration.between(time1,time2);
        String ti =  TimeUtils.miliToString(duration.toMillis());
        assertEquals("00:00:30.0",ti);

    }

    @Test
    public void minute(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        String time = "00:01:30";
        LocalTime time1 = LocalTime.parse(time, dateTimeFormatter);
        time = "00:03:00";
        LocalTime time2 = LocalTime.parse(time, dateTimeFormatter);
        Duration duration = Duration.between(time1,time2);
        String ti =  TimeUtils.miliToString(duration.toMillis());
        assertEquals("00:01:30.0",ti);

    }

    @Test
    public void hour(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        String time = "00:01:30";
        LocalTime time1 = LocalTime.parse(time, dateTimeFormatter);
        time = "01:03:00";
        LocalTime time2 = LocalTime.parse(time, dateTimeFormatter);
        Duration duration = Duration.between(time1,time2);
        String ti =  TimeUtils.miliToString(duration.toMillis());
        assertEquals("01:01:30.0",ti);

    }

}
