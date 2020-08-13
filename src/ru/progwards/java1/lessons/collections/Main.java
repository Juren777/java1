package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        System.out.println(Creator.fillEven(6));
//        System.out.println(Creator.fillOdd(6));
//        System.out.println(Creator.fill3(3));

        Collection<Integer> li = new ArrayList<>();

        li.add(11); // 0
        li.add(29);  // 1
        li.add(4);  // 2
        li.add(25); // 3
        li.add(14); // 4
        li.add(5);  // 5
        li.add(76);  // 6
        li.add(15); // 7

        //Finder.findMinSumPair(li);sout

        System.out.println(li);
        System.out.println(Finder.findLocalMax(li));


    }
}
