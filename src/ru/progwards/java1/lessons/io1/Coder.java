package ru.progwards.java1.lessons.io1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Coder {
    static void writer(String outFileName, String out) {
        try {
            FileWriter fileWriter = new FileWriter(outFileName);
            try {
                fileWriter.write(out);
            } finally {
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try {
            FileInputStream fis = new FileInputStream(inFileName);
            byte[] ch = fis.readAllBytes();
            String out = new String();
            for (byte b : ch) {
                out += code[b];
            }
            writer(outFileName, out);
        } catch (IOException e) {
            writer(logName, e.getMessage());
        }
    }
}
