package ru.progwards.java1.lessons.datetime;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

public class StatisticInfo {

    // start time
    long startTime;
    // - имя секции
    public String sectionName;

    long delta;

    //  - полное время выполнения секции в миллисекундах.
    public int fullTime;

    //  - чистое время выполнения секции в миллисекундах. Для вложенных секций,
    //  из времени выполнения внешней секции нужно вычесть времена выполнения вложенных секций.
    public int selfTime;

    //  - количество вызовов. В случае, если вызовов более одного,
    //  fullTime и selfTime содержат суммарное время выполнения всех вызовов.
    public int count;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getSectionName() {
        return sectionName;
    }

    @Override
    public String toString() {
        return "StatisticInfo{" +
                "startTime=" + startTime +
                ", sectionName='" + sectionName + '\'' +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
                '}';
    }

    Comparator<StatisticInfo> byName = Comparator.comparing(StatisticInfo::getSectionName);

}
