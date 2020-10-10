package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Insurance {

    public static enum FormatStyle {SHORT, LONG, FULL} // стиль формата даты-времени

    private ZonedDateTime start; // дата-время начала действия страховки.

    private Duration duration; // продолжительность действия.

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public Duration getDuration() {
        return duration;
    }

    //  установить продолжительность действия страховки.
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    //  установить продолжительность действия страховки, задав дату-время окончания.
    public void setDuration(ZonedDateTime expiration){
        this.duration =  Duration.between(getStart(),expiration);

    }

    // установить дату-время начала действия страховки.
    public Insurance(ZonedDateTime start){
        this.start = start;
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

        if (style == FormatStyle.SHORT){
            DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate ld = LocalDate.from(dtf.parse(strStart));
            this.start = ld.atStartOfDay(ZoneId.systemDefault());
        } else if (style == FormatStyle.LONG){
            DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime ld = LocalDateTime.from(dtf.parse(strStart));
            this.start = ld.atZone(ZoneId.systemDefault());
        } else if (style == FormatStyle.FULL){
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
