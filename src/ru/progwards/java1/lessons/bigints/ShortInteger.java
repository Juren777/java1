package ru.progwards.java1.lessons.bigints;

public class ShortInteger extends AbsInteger{
    Short var;

    public ShortInteger(Short var) {
        this.var = var;
    }

    @Override
    public Integer getVar() {
        return var.intValue();
    }

    @Override
    public String toString() {
        return var.toString();
    }


}
