package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        System.out.println(Creator.fillEven(6));
//        System.out.println(Creator.fillOdd(6));
//        System.out.println(Creator.fill3(3));

        Collection<Integer> li = new ArrayList<>();

        li.add(1); // 0
        li.add(2);  // 1
        li.add(3);  // 2
        li.add(4); // 3
        li.add(8); // 4
        li.add(5);  // 5
        li.add(6);  // 6
        li.add(7); // 7

        Integer[] arr = {1,8,5,3,2,4,5,8,6};
        ArrayIterator<Integer> ai = new ArrayIterator<>(arr);
        System.out.println();

        //Finder.findMinSumPair(li);sout

//        System.out.println(li);
//        System.out.println(Finder.findLocalMax(li));

//
//        System.out.println(li.size());
//        System.out.println(Finder.findSequence(li));

//        Collection<String> sc = new ArrayList<>();
//
//        sc.add("Александр");
//        sc.add("Дмитрий");
//        sc.add("Александр");
//        sc.add("Григорий");
//        sc.add("Григорий");
//        sc.add("Борис");
//        sc.add("Григорий");
//        sc.add("Василий");
//        sc.add("Григорий");
//        sc.add("Григорий");
//        sc.add("Василий");
//
//
//        System.out.println(Finder.findSimilar(sc));
    }
}
