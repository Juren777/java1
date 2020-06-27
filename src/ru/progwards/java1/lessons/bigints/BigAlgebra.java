package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {

    public static BigDecimal fastPow(BigDecimal num, int pow){
        BigDecimal res = new BigDecimal("1");
        while (pow != 0){
            if ((pow & 1) == 1){
                res = res.multiply(num);
            }
            num = num.multiply(num);
            pow >>= 1;
        }
        return res;
    }

    public static BigInteger fibonacci(int n){
        BigInteger fib = new BigInteger("1");
        BigInteger  prev = new BigInteger("1");
        BigInteger  old;
        for (int i = 2; i < n; i++) {
            old = prev;
            prev = fib;
            fib = old.add(prev);
        }
        return fib;
    }
}
