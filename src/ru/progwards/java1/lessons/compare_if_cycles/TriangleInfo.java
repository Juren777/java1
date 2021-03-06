package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {

    public static boolean isTriangle(int a, int b, int c) {
        if (a < b + c && b < a + c && c < a + b)
            return true;
        else
            return false;
    }
    public static boolean isRightTriangle(int a, int b, int c){
        if (a*a != b*b + c*c && b*b != a*a + c*c && c*c != a*a + b*b)
            return false;
        else
            return true;
    }
    public static boolean isIsoscelesTriangle(int a, int b, int c){
        if (a == b || b == c || a == c)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        System.out.println(isTriangle(4,4,5));
        System.out.println(isRightTriangle(4,4,5));
        System.out.println(isIsoscelesTriangle(9,4,9));
    }
}
