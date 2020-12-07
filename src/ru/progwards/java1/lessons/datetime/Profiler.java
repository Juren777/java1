package ru.progwards.java1.lessons.datetime;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Profiler {
    private static List<StatisticInfo> statisticInfos = new ArrayList<>();

    static Deque<StatisticInfo> stack = new ArrayDeque<>();

    private static int getIndex(String name) {
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
        StatisticInfo statisticInfo = new StatisticInfo();
        statisticInfo.startTime = System.currentTimeMillis();
        statisticInfo.sectionName = name;
        stack.push(statisticInfo);
        if (getIndex(name) == -1)
            statisticInfos.add(statisticInfo);
    }

    //  - выйти из профилировочной секции.
    //  Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах.
    public static void exitSection(String name) {

        StatisticInfo statisticInfo = stack.pop(); // this stat level

        long time = (int) (System.currentTimeMillis() - statisticInfo.startTime);
        int i = getIndex(name);
        statisticInfos.get(i).fullTime += time;
        statisticInfos.get(i).count++;
    }

    //  - получить профилировочную статистику, отсортировать по наименованию секции
    public static List<StatisticInfo> getStatisticInfo() {
        Collections.sort(statisticInfos, Comparator.comparing(StatisticInfo::getSectionName));
        return statisticInfos;
    }


    public static void main(String[] args) {

        enterSection("1");
        for (int i = 0; i < 1; i++) {
            enterSection("2");

            enterSection("3");
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            exitSection("3");
            try {
                TimeUnit.MILLISECONDS.sleep(10);
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


        for (StatisticInfo si : getStatisticInfo()
        ) {
            System.out.println(si);
        }
    }
}
