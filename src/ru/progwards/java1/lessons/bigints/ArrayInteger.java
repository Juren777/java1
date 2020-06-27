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
        for (int i = 0; i < size; i++){
            digits[i] = value.mod(BigInteger.TEN).byteValue();
            value = value.divide(BigInteger.TEN);
        }
        System.out.println(Arrays.toString(digits));
    }

    BigInteger toInt(){
        String val = "";
        for (int i = digits.length - 1; i >=0 ; i--){
            val += digits[i];
        }
        return new BigInteger(val);
    }

    boolean add(ArrayInteger num){

        for (int i = 0; i < digits.length; i++ ){
            if ((this.digits[i] + num.digits[i]) > 9 ){
                return false;
            }
            this.digits[i] += num.digits[i];
        }


        return true;
    }

    public static void main(String[] args) {
        ArrayInteger arrayInteger1 = new ArrayInteger(3);
        arrayInteger1.fromInt(new BigInteger("435"));
        System.out.println("toInt = " + arrayInteger1.toInt());
        ArrayInteger arrayInteger2 = new ArrayInteger(3);
        arrayInteger2.fromInt(new BigInteger("112"));
        arrayInteger1.add(arrayInteger2);
        System.out.println("toInt = " + arrayInteger1.toInt());
    }
}
