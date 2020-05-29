package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {
    static final double PI = 3.14;

    public static double volumeBallDouble(double radius){
        return 4.0/3.0*PI*radius*radius*radius;
    }

    public static float volumeBallFloat(float radius){
        return  (4f/3f*( (float)PI)*radius*radius*radius);
    }

    public static double calculateAccuracy(double radius){
        return volumeBallDouble(radius) - volumeBallFloat((float) radius);
    }
    public static void main(String[] args) {
        System.out.println("double = " + volumeBallDouble(6371.21));
        System.out.println("float  = " + volumeBallFloat(6371.2f));
        System.out.println("calculate = " + calculateAccuracy(6371.2));
    }
}
