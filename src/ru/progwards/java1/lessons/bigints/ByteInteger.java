package ru.progwards.java1.lessons.bigints;

public class ByteInteger extends AbsInteger{

    Byte var;

    public ByteInteger(Byte var) {
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
