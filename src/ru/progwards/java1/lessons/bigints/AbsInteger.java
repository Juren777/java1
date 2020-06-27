package ru.progwards.java1.lessons.bigints;

public abstract class AbsInteger {

    protected abstract Integer getVar();

    static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        Integer res = num1.getVar() + num2.getVar();

        if (res >= -128 && res <= 127) {
            return new ByteInteger(res.byteValue());
        } else if (res >= -32768 && res <= 32767){
            return new ShortInteger(res.shortValue());
        } else {
            return new IntInteger(res);
        }
    }



    public static void main(String[] args) {

        ByteInteger byteInteger = new ByteInteger((byte)12);
        ShortInteger shortInteger = new ShortInteger((short)15);
        System.out.println(add(byteInteger, shortInteger));

    }
}
