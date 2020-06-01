package ru.progwards.java1.lessons.bitsworld;

public class Binary {

    byte num;

    public Binary(byte num){
        this.num = num;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i =0; i < 8; i++){
            res = (num & 0b1) + res;
            num >>>= 1;
        }
        return res;
    }
}








/*3.1 Реализовать конструктор
public Binary(byte num).
        3.2 Реализовать метод
public String toString(), который возвращает двоичное представление числа типа byte
, используя только битовые операции. В выводимом значении всегда должно быть 8 символов
        Например:
        0: "00000000"
        1: "00000001"
        127: "01111111"
        -128: "10000000"
        -1: "11111111"*/
