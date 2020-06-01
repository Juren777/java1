package ru.progwards.java1.lessons.bitsworld;

import static ru.progwards.java1.lessons.bitsworld.CheckBit.checkBit;
import static ru.progwards.java1.lessons.bitsworld.SumBits.sumBits;

public class Main {



    public static void main(String[] args) {
//        System.out.println(" -- sumBits  = " + sumBits((byte) 0b101_1101));
//        System.out.println(" -- checkBit = " + checkBit((byte) 0b0101_0001,4));
        Binary binary = new Binary((byte) 17);
        System.out.println(binary.toString());



    }
}
