package ru.progwards.java1.lessons.classes;

import ru.progwards.java1.lessons.interfaces.FoodCompare;

import java.util.Objects;

public class Animal implements FoodCompare{

    @Override
    public int compareFoodPrice(Animal animal) {
        return Double.compare(this.getFoodPrice(), animal.getFoodPrice());
    }

    enum AnimalKind {ANIMAL, COW, HAMSTER, DUCK}

    enum FoodKind {UNKNOWN, HAY, CORN}

    public double getFood1kgPrice(){
        switch (getFoodKind()){
            case HAY: return 20;
            case CORN: return 50;
            default: return 0;
        }

    }
    public double getFoodPrice(){
        return calculateFoodWeight() * getFood1kgPrice();
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

}
