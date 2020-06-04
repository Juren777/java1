package ru.progwards.java1.lessons.interfaces;

import java.util.Objects;

public class Animal implements FoodCompare, CompareWeight{
    double weight;

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        switch (Double.compare(this.getWeight(), smthHasWeigt.getValue())){
            case -1: return CompareResult.LESS;
            case 0: return CompareResult.EQUAL;
            case 1: return CompareResult.GREATER;
        }

        return null;
    }

    @Override
    public double getValue() {
        return getWeight();
    }

    public double getWeight() {
        return this.weight;
    }
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

    public Animal(double weight) {
        this.weight = weight;
    }

    public AnimalKind getKind() {
        return AnimalKind.ANIMAL;
    }

    public FoodKind getFoodKind() {
        return FoodKind.UNKNOWN;
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
