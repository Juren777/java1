package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws IOException {
        String out = "";
        try {
            FileReader fileReader = new FileReader(inFileName);
            try {
                Scanner scanner = new Scanner(fileReader);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    for (char ch : line.toCharArray()) {
                        if (filter.indexOf(ch) == -1) {
                            out += ch;
                        }
                    }
                    out += "\n";
                }
            } finally {
                fileReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Write File
        try {
            FileWriter fileWriter = new FileWriter(outFileName);
            try{
                fileWriter.write(out);
            } finally {
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
