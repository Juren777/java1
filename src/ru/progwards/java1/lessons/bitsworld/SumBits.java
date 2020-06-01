package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value){
        int sum = 0;
        while (value > 0) {
            if ((value & 1) == 1){
                sum +=1;
            };
            value >>>= 1;
        }
        return sum;
    }
}
