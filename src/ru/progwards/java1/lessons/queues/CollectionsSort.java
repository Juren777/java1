package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {
    public static void mySort(Collection<Integer> data) {
        Integer[] a = data.toArray(new Integer[data.size()]);
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
        data.clear();
        data.addAll(Arrays.asList(a));
    }
    public static void minSort(Collection<Integer> data){
        Collection<Integer> newData = new ArrayList<>();
        Integer i;
        while (data.size() != 0){
            i = Collections.min(data);
            newData.add(i);
            data.remove(i);
        }
        data.addAll(newData);
    }
    public static void collSort(Collection<Integer> data){
        Collections.sort((List)data);
    }

    public static Collection<String> compareSort(){


        return null;
    }

    public static void main(String[] args) {
        Collection<Integer> data = new ArrayList<>();
        Collections.addAll(data, 1, 8, 6, 4, 9, 2, 5, 3, 7, 4, 6, 5, 7);

        System.out.println(data);
//        mySort(data);
//        minSort(data);
        collSort(data);
        System.out.println(data);
    }
}
