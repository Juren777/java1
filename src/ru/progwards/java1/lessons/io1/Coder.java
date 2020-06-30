package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Coder {
    static void writer(String outFileName, String out){
        try{
            FileWriter fileWriter = new FileWriter(outFileName, true);
            try{
                fileWriter.write(out + "\n");
            } finally {
                fileWriter.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try {
            FileReader fileReader = new FileReader(inFileName);
            try{
                Scanner scanner = new Scanner(fileReader);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String out = new String();
                    for (char ch : line.toCharArray()) {
                        int v = ch;
                        out += code[v];
                        }
                    writer(outFileName, out);
                    }

            }finally {
                fileReader.close();
            }
        } catch (IOException e) {
            writer(logName, e.getMessage());
        }
    }
}
