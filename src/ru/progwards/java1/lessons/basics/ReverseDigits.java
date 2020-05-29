package ru.progwards.java1.lessons.basics;

import com.sun.security.jgss.GSSUtil;

public class ReverseDigits {

    public static int reverseDigits(int number){
        String rev = Integer.toString(number%10) + (number /= 10)%10 + (number / 10)%10;
        return Integer.parseInt(rev);
    }

    public static void main(String[] args) {
        System.out.println(reverseDigits(123));

    }
}
