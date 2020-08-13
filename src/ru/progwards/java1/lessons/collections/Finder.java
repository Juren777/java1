package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {

    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {

        LinkedList ll = new LinkedList(numbers);
        ListIterator<Integer> li1 = ll.listIterator();
        ListIterator<Integer> li2 = ll.listIterator(1);

        List<Integer> ret = new ArrayList<>(2);

        Integer oldSum = null;
        Integer newSum;

        while (li1.hasNext()) {
            if (li2.hasNext() && oldSum == null) {
                oldSum = li1.next() + li2.next();
                ret.add(li1.previousIndex());
                ret.add(li2.previousIndex());
            } else if (li2.hasNext() && oldSum != null){
                newSum = li1.next() + li2.next();
                if (newSum < oldSum){
                    ret.set(0, li1.previousIndex());
                    ret.set(1, li2.previousIndex());
                    oldSum = newSum;
                }
            } else {
                break;
            }
        }
        return ret;
    }
    public static Collection<Integer> findLocalMax(Collection<Integer> numbers){

        List<Integer> li = new ArrayList<>(numbers);
        Collection<Integer> ci = new ArrayList<>();

        for (int i = 1; i < li.size() - 1; i++) {

            if (li.get(i) > li.get(i - 1) && li.get(i) > li.get(i + 1)){
                ci.add(li.get(i));
            }
        }
        return ci;
    }

    public static boolean findSequence(Collection<Integer> numbers){
        boolean ret = true;
        for (int i = 1; i <= numbers.size(); i++) {
            if (!numbers.contains(i)){
                ret = false;
            }
        }
        return ret;
    }

    public static String findSimilar(Collection<String> names){

        String ret = "";
        List<String> cs1 = new ArrayList<>(names);
        List<String> cs2 = new ArrayList<>(names);
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < cs1.size(); i++) {
            for (int j = 0; j < cs2.size(); j++) {
                if (cs1.get(i).equals(cs2.get(j))){
                    count2++;
                }
                if (count2 > count1){
                    ret = cs1.get(i) + ":" + count2;
                    count1 = count2;
                }
            }
            count2 = 0;
        }
        return ret;
    }
}
