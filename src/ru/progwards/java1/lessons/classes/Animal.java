package ru.progwards.java1.lessons.classes;

public class Animal {
    enum AnimalKind {ANIMAL, COW, HAMSTER, DUCK}

    enum FoodKind {UNKNOWN, HAY, CORN}

    double weight;

    public Animal(double weight) {
        this.weight = weight;
    }

    public AnimalKind getKind() {
        return AnimalKind.ANIMAL;
    }

    public FoodKind getFoodKind() {
        return FoodKind.UNKNOWN;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getFoodCoeff(){
        return 0.02;
    }

    public double calculateFoodWeight(){
        return weight*getFoodCoeff();
    }

    @Override
    public String toString() {
        return "I am " + getKind() + ", eat " + getFoodKind();
    }
    public String toStringFull(){
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight();

    }

    public static void main(String[] args) {
//        Animal animal = new Animal(0d);
//        System.out.println(animal.toStringFull());
//        Cow cow = new Cow(720);
//        System.out.println(cow.toStringFull());
    }

}
