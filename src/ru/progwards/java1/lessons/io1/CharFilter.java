package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws IOException {

        try {
            FileReader fileReader = new FileReader(inFileName);
            try {
                Scanner scanner = new Scanner(fileReader);
                while (scanner.hasNextLine()) {
                    String out = new String();
                    String line = scanner.nextLine();
                    for (char ch : line.toCharArray()) {
                        if (filter.indexOf(ch) == -1) {
                            out += ch;
                        }
                    }
                    try {
                        FileWriter logFile = new FileWriter(outFileName, true);
                        try {
                            logFile.write(out + "\n");
                        } finally {
                            logFile.close();
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                }
            } finally {
                fileReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
