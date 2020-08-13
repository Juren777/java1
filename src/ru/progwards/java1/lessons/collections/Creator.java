package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Creator {

    public static Collection<Integer> fillEven(int n) {
        Collection<Integer> ci = new ArrayList<>();
        for (int i = 2; i < n * 2 + 2; i += 2) {
            ci.add(i);
        }
        return ci;
    }

    public static Collection<Integer> fillOdd(int n) {
        Collection<Integer> ci = new ArrayList<>();
        for (int i = 1; i < n * 2; i += 2) {
            ci.add(i);
        }
        Collections.reverse((List<?>) ci);
        return ci;
    }

    public static Collection<Integer> fill3(int n){
        Collection<Integer> ci = new ArrayList<>();
        for (int i = 0; i < n*3; i += 3) {
            ci.add(i);
            ci.add(i*i);
            ci.add(i*i*i);
        }
        return ci;
    }
}
