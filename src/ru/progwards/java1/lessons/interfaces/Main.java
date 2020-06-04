package ru.progwards.java1.lessons.interfaces;

public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Cow(750);
        Animal animal2 = new Hamster(9);
        System.out.println(animal1.equals(animal2));
//        System.out.println(animal1.compareFoodPrice(animal2));
//        System.out.println(CalculateFibonacci.fiboNumber(7));
//        System.out.println(CalculateFibonacci.fiboNumber(7));
//        System.out.println(CalculateFibonacci.fiboNumber(8));
        System.out.println(animal1.compareWeight(animal2));
        System.out.println(new Food(5).compareWeight(new Food(5)));

    }
}
