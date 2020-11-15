package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Profiler {
    private static List<StatisticInfo> statisticInfos = new ArrayList<>();
    private static int lastTime;

    private static int getIndexName(String name){
        for (int i = 0; i < statisticInfos.size(); i++
        ) {
            if (statisticInfos.get(i).sectionName.equals(name)) {
                return i;
            }
        }
        return -1;
    }
    //  - войти в профилировочную секцию, замерить время входа.
    public static void enterSection(String name) {
        StatisticInfo statisticInfo;
        if (getIndexName(name) == -1){
            statisticInfo = new StatisticInfo();
            lastTime = 0;
            statisticInfo.sectionName = name;
            statisticInfo.startTime = System.currentTimeMillis();
            statisticInfos.add(statisticInfo);
        }

    }

    //  - выйти из профилировочной секции.
    //  Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах.
    public static void exitSection(String name) {
        int i = getIndexName(name);
        statisticInfos.get(i).endTime = System.currentTimeMillis();
        statisticInfos.get(i).fullTime = (int) (statisticInfos.get(i).endTime - statisticInfos.get(i).startTime);
        statisticInfos.get(i).count++;
    }

    //  - получить профилировочную статистику, отсортировать по наименованию секции
    public static List<StatisticInfo> getStatisticInfo() {


        return statisticInfos;
    }


    public static void main(String[] args) {

        enterSection("1");
        for(int i = 0;i < 100; i++) {
            enterSection("2");

                enterSection("3");
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                exitSection("3");
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            exitSection("2");
        }
        enterSection("4");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exitSection("4");
        exitSection("1");


        for (StatisticInfo si: getStatisticInfo()
             ) {
            System.out.println(si);
        }
    }
}
