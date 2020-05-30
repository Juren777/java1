package ru.progwards.java1.lessons.classes;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal(0d);
        System.out.println(animal.toStringFull());
        Cow cow = new Cow(720);
        System.out.println(cow.toStringFull());
        ComplexNum complexNum1 = new ComplexNum(1, 52);
        ComplexNum complexNum2 = new ComplexNum(5,7);
        ComplexNum complexNum3 = complexNum1.add(complexNum2);
        System.out.println(complexNum3.a + "  " + complexNum3.b);
    }
}
