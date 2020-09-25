package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class FiboMapCache {

    private Map<Integer, BigDecimal> fiboCache = new HashMap<>();

    //  включен ли кэш. При cacheOn = true кэш работает, при cacheOn = false - выключен
    private boolean cacheOn;

    public FiboMapCache(boolean cacheOn){
        this.cacheOn = cacheOn;
    }

    private BigDecimal getFibo(int n){
        if (n == 1 || n == 2) {
            return BigDecimal.valueOf(1l);
        } else {
            BigDecimal fib = BigDecimal.valueOf(1l);
            BigDecimal prev = BigDecimal.valueOf(1l);
            BigDecimal old;
            for (int i = 2; i < n; i++) {
                old = prev;
                prev = fib;
                fib = old.add(prev);
            }
            return fib;
        }
    }


    public BigDecimal fiboNumber(int n){

        if (cacheOn && !fiboCache.isEmpty() && fiboCache.containsKey(n)){
            return fiboCache.get(n);
        }else {
            BigDecimal fibo = getFibo(n);
            if (cacheOn){
                fiboCache.put(n, fibo);
            }
            return fibo;
        }
    }

    public void clearCahe(){
        fiboCache.clear();
    }

    public static void test(){
        FiboMapCache fiboMapCache1 = new FiboMapCache(true);
        FiboMapCache fiboMapCache2 = new FiboMapCache(true);
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            fiboMapCache1.fiboNumber(i);
        }
        System.out.println("fiboNumber cacheOn=true время выполнения " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            fiboMapCache2.fiboNumber(i);
        }
        System.out.println("fiboNumber cacheOn=false время выполнения " + (System.currentTimeMillis() - start));
    }
    public static void main(String[] args) {
        test();
    }
}
