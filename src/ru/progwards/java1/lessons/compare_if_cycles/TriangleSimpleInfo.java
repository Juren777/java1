package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {

    public static int maxSide(int a, int b, int c){
        if (a > b && a > c) {
            return a;
        } else if (b > c){
            return b;
        } else {
            return c;
        }
    }
    public static int minSide(int a, int b, int c){
        if (a < b && a < c) {
            return a;
        } else if (b < c){
            return b;
        } else {
            return c;
        }
    }
    public static boolean isEquilateralTriangle(int a, int b, int c){
        if (a == b && b == c)
            return true;
        else
            return false;
    }
    public static void main(String[] args) {
        System.out.println(maxSide(7,7,7));
        System.out.println(minSide(7,7,7));
        System.out.println(isEquilateralTriangle(7,7,7));
    }
}
