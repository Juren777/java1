package ru.progwards.java1.lessons.io1;



public class Main {


    public static void main(String[] args) {

        char[] code = new String("ffffffffffffffffffffffffffffffffffffffffffffffffabcdefghijklmnop").toCharArray();
        Coder.codeFile("file.txt", "file1.txt", code, "log.txt");

    }
}
