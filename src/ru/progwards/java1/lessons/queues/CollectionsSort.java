package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {

//    public static void mySort(Collection<Integer> data) {
//        Integer[] a = data.toArray(new Integer[data.size()]);
//        for (int i = 0; i < a.length; i++) {
//            for (int j = i + 1; j < a.length; j++) {
//                int c;
//                if (a[i] > a[j]) {
//                    c = a[i];
//                    a[i] = a[j];
//                    a[j] = c;
//                }
//            }
//        }
//        data.clear();
//        data.addAll(Arrays.asList(a));
//    }

    public static void mySort(Collection<Integer> data) {
        List<Integer> a = new ArrayList<>(data);
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                int c;
                if (a.get(i) > a.get(j)) {
                    c = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, c);
                }
            }
        }
        data.clear();
        data.addAll(a);
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

        // return collection
        Collection<String> cs = new ArrayDeque<>();

        class MethodSort implements Comparable<MethodSort>{
            String method;
            Long time;

            MethodSort(String method, Long time) {
                this.method = method;
                this.time = time;
            }

            @Override
            public int compareTo(MethodSort o) {
                int c;
                c = Long.compare(time, o.time);
                if (c == 0){
                    c = String.CASE_INSENSITIVE_ORDER.compare(method, o.method);
                }
                return c;
            }

            @Override
            public String toString() {
                return method;
            }
        }

        PriorityQueue<MethodSort> priorityQueue = new PriorityQueue<>();

        // data for sort
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            data.add(i);
        }

        Collections.shuffle(data);
        long start = System.currentTimeMillis();
        minSort(data);
        priorityQueue.offer(new MethodSort("minSort", System.currentTimeMillis() - start));

        Collections.shuffle(data);
        start = System.currentTimeMillis();
        mySort(data);
        priorityQueue.offer(new MethodSort("mySort", System.currentTimeMillis() - start));

        Collections.shuffle(data);
        start = System.currentTimeMillis();
        collSort(data);
        priorityQueue.offer(new MethodSort("collSort", System.currentTimeMillis() - start));

        while(!priorityQueue.isEmpty()){
            cs.add(priorityQueue.poll().toString());
        }
        return cs;
    }

    public static void main(String[] args) {
        System.out.println(compareSort());
    }
}
