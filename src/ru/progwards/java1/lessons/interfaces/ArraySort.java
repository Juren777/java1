package ru.progwards.java1.lessons.interfaces;

public class ArraySort implements CompareWeight{
    public static void sort(CompareWeight[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                CompareWeight c;
                if (a[i].getValue() > a[j].getValue()) {
                    c = a[i];
                    a[i] = a[j];
                    a[j] = c;
                }
            }

        }
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return null;
    }

    @Override
    public double getValue() {
        return 0;
    }
}
