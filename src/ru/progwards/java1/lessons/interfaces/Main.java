package ru.progwards.java1.lessons.interfaces;

import ru.progwards.java1.lessons.bigints.BigAlgebra;
import ru.progwards.java1.lessons.bigints.ByteInteger;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ByteInteger byteInteger = new ByteInteger((byte)12);
        System.out.println(byteInteger.toString());

//        BigAlgebra bigAlgebra = new BigAlgebra();
//
//        BigDecimal result = bigAlgebra.fastPow(new BigDecimal("5"), 4);
//        System.out.println(result);
//        System.out.println(bigAlgebra.fibonacci(12));
//        Animal animal1 = new Cow(750);
//        Animal animal2 = new Hamster(9);
//        System.out.println(animal1.equals(animal2));
//        System.out.println(animal1.compareFoodPrice(animal2));
//        CalculateFibonacci.fiboNumber(29);
//        System.out.println(CalculateFibonacci.fiboNumber(29));
//        CalculateFibonacci.clearLastFibo();
//        System.out.println(CalculateFibonacci.fiboNumber(29));
//        System.out.println(animal1.compareWeight(animal2));
//        System.out.println(new Food(5).compareWeight(new Food(5)));

    }
}
