package ru.progwards.java1.lessons.bigints;

public class IntInteger extends AbsInteger{
    Integer var;

    public IntInteger(Integer var) {
        this.var = var;
    }

    public Integer getVar() {
        return var;
    }

    @Override
    public String toString() {
        return var.toString();
    }
}
