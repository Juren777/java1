package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    int a, b;
    public ComplexNum(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + "+" + b + "i";
    }
}
