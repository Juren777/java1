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
        if (this.digits.length < num.digits.length){
            return false;
        }
        int dec = 0;
        for (int i = 0; i < digits.length; i++ ){
            if (i == num.digits.length){
                return false;
            }
            if ((this.digits[i] + num.digits[i]) < 10){
                this.digits[i] += (num.digits[i] + dec);
                dec = 0;
            } else{
                this.digits[i] += (num.digits[i] + dec);
                this.digits[i] %= 10;
                dec = 1;
            }

        }


        return true;
    }

    public static void main(String[] args) {
        ArrayInteger ai1 = new ArrayInteger(7);
        ai1.fromInt(new BigInteger("9702436"));
        System.out.println("toInt = " + ai1.toInt());
        ArrayInteger ai2 = new ArrayInteger(5);
        ai2.fromInt(new BigInteger("81293"));
        ai1.add(ai2);
        System.out.println("toInt = " + ai1.toInt()); //9783729
    }
}
