package ru.progwards.java1.lessons.bigints;

public abstract class AbsInteger {

    static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        Integer res;
        if (num1 instanceof IntInteger || num2 instanceof IntInteger) {
            res = ((IntInteger) num1).getVar() + ((IntInteger) num2).getVar();

        } else if (num1 instanceof ShortInteger || num2 instanceof ShortInteger) {
            res = ((ShortInteger) num1).getVar() + ((ShortInteger) num2).getVar();
        } else {
            res = ((ByteInteger) num1).getVar() + ((ByteInteger) num2).getVar();
        }
        if (res >= -128 && res <= 127) {
            return new ByteInteger(res.byteValue());
        } else if (res >= -32768 && res <= 32767){
            return new ShortInteger(res.shortValue());
        } else {
            return new IntInteger(res);
        }
    }
}
