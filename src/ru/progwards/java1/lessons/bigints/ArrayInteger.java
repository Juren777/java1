package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {

    byte[] digits;

    ArrayInteger(int n){
        this.digits = new byte[n];
    }

    void fromInt(BigInteger value){
        int size = value.toString().length();
        for (int i = size - 1; i >= 0; i--){
            digits[i] = value.mod(BigInteger.TEN).byteValue();
            value = value.divide(BigInteger.TEN);
        }
        System.out.println(Arrays.toString(digits));
    }

    BigInteger toInt(){
        String val = "";
        for (int i = 0; i < digits.length; i++){
            val += digits[i];
        }
        return new BigInteger(val);
    }

/*    public static void main(String[] args) {
        ArrayInteger arrayInteger = new ArrayInteger(3);

        arrayInteger.fromInt(new BigInteger("935"));
        System.out.println("toInt = " + arrayInteger.toInt());
    }*/
}
