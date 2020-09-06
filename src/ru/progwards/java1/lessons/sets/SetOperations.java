package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {


    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> res = new HashSet<>(set1);
        res.addAll(set2);
        return res;
    }
    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> res = new HashSet<>(set1);
        res.retainAll(set2);
        return res;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> res = new HashSet<>(set1);
        res.removeAll(set2);
        return res;
    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> res1 = new HashSet<>(set1);
        Set<Integer> res2 = new HashSet<>(set2);
        res1.removeAll(set2);
        res2.removeAll(set1);
        res1.addAll(res2);
        return res1;
    }


    public static void main(String[] args) {
        Set<Integer> set1 = Set.of(1,2,3,4);
        Set<Integer> set2 = Set.of(4,5,6,3);

        System.out.println(union(set1,set2));
        System.out.println(intersection(set1,set2));
        System.out.println(difference(set1,set2));
        System.out.println(symDifference(set1,set2));
    }
}
