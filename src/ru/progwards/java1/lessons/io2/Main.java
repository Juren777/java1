package ru.progwards.java1.lessons.io2;

public class Main {

    public static void main(String[] args) {

        //    System.out.println(PhoneNumber.format("8(999)111-22-33"));
        Translator tr = new Translator(new String[]{"hello", "world"}, new String[]{"привет", "мир"});
        String out = tr.translate("Hello, World!!!");
        System.out.println(out);
    }
}
