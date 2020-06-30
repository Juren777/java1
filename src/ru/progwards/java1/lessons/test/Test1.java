package ru.progwards.java1.lessons.test;

import ru.progwards.java1.lessons.io1.CharFilter;
import ru.progwards.java1.lessons.io1.LineCount;

import java.io.IOException;

public class Test1 {

    public static void main(String[] args) {
    //    System.out.println("Сделаю коммит, запушу в репо: глупый робот, проверяй теперь всё это...");
    //    System.out.println(LineCount.calcEmpty("file.txt"));
        try {
            CharFilter.filterFile("file.txt", "file1.txt","- —,.()");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
