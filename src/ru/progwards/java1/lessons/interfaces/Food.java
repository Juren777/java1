package ru.progwards.java1.lessons.interfaces;


public class Food implements CompareWeight{
    private int weight;

    public Food(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

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
}
