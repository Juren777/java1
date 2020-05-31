package ru.progwards.java1.lessons.arrays;

public class ArraySort {
    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int c;
                if (a[i] > a[j]) {
                    c = a[i];
                    a[i] = a[j];
                    a[j] = c;
                }
            }

        }
    }
}
