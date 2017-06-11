package com.liulangf.threeten;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * User: Bill Bejeck
 * Date: 3/4/14
 * Time: 9:56 PM
 */
public class LocalDateTimeTest {

	private Logger log = LoggerFactory.getLogger(getClass());
	
    @Test
    public void test_add_minutes() {
        LocalDateTime localDateTime = LocalDateTime.parse("2003-11-18T10:40:00");
        LocalDateTime adjustedDateTime = localDateTime.plusMinutes(30);
        assertThat(adjustedDateTime.toString(), is("2003-11-18T11:10"));
    }


    @Test
    public void test_truncate_minutes_seconds() {
        LocalDateTime localDateTime = LocalDateTime.parse("2009-06-01T11:35:17");
        LocalDateTime truncatedMinutes = localDateTime.truncatedTo(ChronoUnit.HOURS);
        assertThat(truncatedMinutes.toString(), is("2009-06-01T11:00"));
    }


    @Ignore
    @Test
    public void test_adjust_local_date_time_zone() {
        LocalDateTime eastCoastTime = LocalDateTime.now(Clock.system(OffsetTime.now().getOffset()));
        LocalDateTime centralTime = LocalDateTime.now(Clock.system(ZoneId.of("America/Chicago")));
        LocalDateTime pacificTime = LocalDateTime.now(Clock.system(ZoneId.of("America/Los_Angeles")));
        assertThat(eastCoastTime.get(ChronoField.CLOCK_HOUR_OF_DAY) - pacificTime.get(ChronoField.CLOCK_HOUR_OF_DAY),is(3));
        assertThat(eastCoastTime.get(ChronoField.CLOCK_HOUR_OF_DAY) - centralTime.get(ChronoField.CLOCK_HOUR_OF_DAY),is(1));
    }
    
    public static Stream<LocalDateTime> localDateTimes(LocalDateTime start, int limit) {
    	return Stream.iterate(start, e -> e.plusMinutes(15))
    			.limit(limit);
    }
    
    @Test
    public void timeStepTest() {
    	Stream<LocalDateTime> stream = localDateTimes(LocalDateTime.now(), 100);
    	stream.forEach(t -> {
    		log.info("{}", t.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")));
    	});
    	
    	
    }
    

}
