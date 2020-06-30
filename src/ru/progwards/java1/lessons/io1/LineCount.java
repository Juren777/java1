package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName){
        int i = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            try {
                Scanner scanner = new Scanner(fileReader);
                while (scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    if (line.trim().isEmpty()){
                        i++;
                    }
                }
            } finally {
                fileReader.close();
            }
        } catch (IOException e){
            return -1;
        }
        return i;
    }
}
