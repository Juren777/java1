package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {

    // start time
    long startTime;
    // end time
    long endTime;
    // - имя секции
    public String sectionName;

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

    @Override
    public String toString() {
        return "StatisticInfo{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", sectionName='" + sectionName + '\'' +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
                '}';
    }
}
