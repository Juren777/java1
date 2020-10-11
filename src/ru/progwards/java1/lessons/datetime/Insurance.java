package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class Insurance {

    public static enum FormatStyle {SHORT, LONG, FULL} // стиль формата даты-времени

    private ZonedDateTime start; // дата-время начала действия страховки.

    private Duration duration; // продолжительность действия.

    // установить дату-время начала действия страховки.
    public Insurance(ZonedDateTime start){
        this.start = start;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    //  установить продолжительность действия страховки.
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    //  установить продолжительность действия страховки, задав дату-время окончания.
    public void setDuration(ZonedDateTime expiration){
        this.duration =  Duration.between(getStart(),expiration);

    }
    // установить продолжительность действия страховки, задав целыми числами количество месяцев, дней и часов.
    public void setDuration(int months, int days, int hours){
        ZonedDateTime endTime = start.plusMonths(months).plusDays(days).plusHours(hours);
        this.duration =  Duration.between(getStart(), endTime);
    }

    // установить продолжительность действия страховки
    public void setDuration(String strDuration, FormatStyle style){
        if (style.equals(FormatStyle.SHORT)){
            this.duration = Duration.ofMillis(Long.parseLong(strDuration));
        }else if (style.equals(FormatStyle.LONG)){
            DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            TemporalAccessor ta = dtf.parse(strDuration);

            ZonedDateTime endTime = start.plusYears(ta.get(ChronoField.YEAR))
                    .plusMonths(ta.get(ChronoField.MONTH_OF_YEAR))
                    .plusDays(ta.get(ChronoField.DAY_OF_MONTH))
                    .plusHours(ta.get(ChronoField.HOUR_OF_DAY))
                    .plusMinutes(ta.get(ChronoField.MINUTE_OF_HOUR))
                    .plusSeconds(ta.get(ChronoField.SECOND_OF_MINUTE));

            this.duration = Duration.between(start, endTime);
        } else if (style.equals(FormatStyle.FULL)){
            this.duration = Duration.parse(strDuration);
        }
    }

    public Duration getDuration() {
        return duration;
    }

    // проверить действительна ли страховка на указанную дату-время. Если продолжительность
    // не задана считать страховку бессрочной.
    public boolean checkValid(ZonedDateTime dateTime){
        if (duration == null){
            return dateTime.compareTo(start) >= 0;
        }
        ZonedDateTime endTime =  this.start.plus(duration);
        return start.compareTo(dateTime)*dateTime.compareTo(endTime) >= 0;
    }

    // установить дату-время начала действия страховки.
    public Insurance(String strStart, FormatStyle style){

        if (style.equals(FormatStyle.SHORT)){
            DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate ld = LocalDate.from(dtf.parse(strStart));
            this.start = ld.atStartOfDay(ZoneId.systemDefault());
        } else if (style.equals(FormatStyle.LONG)){
            DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime ld = LocalDateTime.from(dtf.parse(strStart));
            this.start = ld.atZone(ZoneId.systemDefault());
        } else if (style.equals(FormatStyle.FULL)){
            DateTimeFormatter dtf = DateTimeFormatter.ISO_ZONED_DATE_TIME;
            ZonedDateTime zdt = ZonedDateTime.parse(strStart, dtf);
            this.start = zdt;
        }
    }

    //  "Insurance issued on " + start + validStr, где validStr = " is valid",
    //  если страховка действительна на данный момент и " is not valid", если она недействительна.
    @Override
    public String toString() {
        Instant instant = Instant.now();
        ZonedDateTime zdt= instant.atZone(ZoneId.of("Europe/Moscow"));
        String validStr = checkValid(zdt)?" is valid": " is not valid";
        return "Insurance issued on " + start + validStr;
    }
}
