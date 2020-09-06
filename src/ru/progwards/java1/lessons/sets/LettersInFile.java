package ru.progwards.java1.lessons.sets;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class LettersInFile {

    public static String process(String fileName){
        TreeSet<Character> treeSet = new TreeSet<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        SortedSet<Character> subset = null;
        try(FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader)
        ){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (char ch : line.toCharArray()) {

                    if (ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122 || ch >= 1040 && ch <= 1103)
                        treeSet.add(ch);
                }
            }
            subset = treeSet.subSet('A', 'я');
            for (char c: subset
                 ) {
                sb.append(c);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        process("file.txt");
    }
}
