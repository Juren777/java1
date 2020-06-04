package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {

    private static CacheInfo lastFibo = new CacheInfo();

    public static class CacheInfo {
        public int n; // - число, для которого рассчитываем Фибоначчи
        public int fibo; // - результат расчета
    }

    public static void clearLastFibo() {
        lastFibo = null;
    }

    public static CacheInfo getLastFibo() {
        return lastFibo;
    }


    public static int fiboNumber(int n) {
        if (lastFibo !=null && n == lastFibo.n) {
            return lastFibo.fibo;
        } else {
            if (n == 1 || n == 2) {
                return 1;
            }
            int fib = 1;
            int prev = 1;
            int old;
            for (int i = 2; i < n; i++) {
                old = prev;
                prev = fib;
                fib = old + prev;
            }

            lastFibo.n = n;
            lastFibo.fibo = fib;
            return fib;
        }
    }

}
