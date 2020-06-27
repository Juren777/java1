package ru.progwards.java1.lessons.bigints;

public class ByteInteger extends AbsInteger{

    Byte var;

    public ByteInteger(Byte var) {
        this.var = var;
    }

    public Byte getVar() {
        return var;
    }

    @Override
    public String toString() {
        return var.toString();
    }
}
