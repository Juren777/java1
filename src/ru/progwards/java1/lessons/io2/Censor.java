package ru.progwards.java1.lessons.io2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Censor {

    public static void censorFile(String inoutFileName, String[] obscene){
        try(RandomAccessFile raf = new RandomAccessFile(inoutFileName, "rw")
        ){
            String str;
            StringBuilder out = new StringBuilder();
            while ((str = raf.readLine()) != null){
                for (String s: obscene
                     ) {
                    int l = s.length();
                    StringBuilder to = new StringBuilder();
                    for (int i = 0; i < l; i++){
                        to.append("*");
                    }
                    str = str.replaceAll(s,to.toString());

                }
                out.append(str + '\n');
            }
            raf.seek(0);
            raf.writeBytes(out.toString().trim());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
