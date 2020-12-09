package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Profiler {
    private static List<StatisticInfo> statisticInfos = new ArrayList<>();

    static Deque<String> stack = new ArrayDeque<>();

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

        int i = getIndex(name);

        stack.push(name);
        if (i == -1){
            statisticInfos.add(statisticInfo);
        }
        else {
            statisticInfos.get(i).startTime = statisticInfo.startTime;
            statisticInfos.get(i).delta = 0;
        }

    }

    //  - выйти из профилировочной секции.
    //  Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах.
    public static void exitSection(String name) {

        stack.pop();
        int i = getIndex(name);
        long time = (int) (System.currentTimeMillis() - statisticInfos.get(i).startTime);
        int j = getIndex(stack.peek());
        if (j != -1){
            statisticInfos.get(j).delta += time;
        }
        statisticInfos.get(i).selfTime += time - statisticInfos.get(i).delta;
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
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
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
