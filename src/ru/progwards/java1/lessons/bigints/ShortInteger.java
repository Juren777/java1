package ru.progwards.java1.lessons.bigints;

public class ShortInteger extends AbsInteger{
    Short var;

    public ShortInteger(Short var) {
        this.var = var;
    }

    public Short getVar() {
        return var;
    }

    @Override
    public String toString() {
        return var.toString();
    }
}
