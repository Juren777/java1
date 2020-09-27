package ru.progwards.java1.lessons.maps;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UsageFrequency {

    public void processFile(String fileName){
        File file = new File(fileName);
        try(Scanner scanner = new Scanner(file);
            FileWriter fr = new FileWriter(file.getName())){
            while(scanner.hasNext()){
                fr.write(scanner.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Character, Integer> getLetters(){
        Map<Character, Integer> map = new TreeMap<>();

        try(FileReader fr = new FileReader("wiki.test.tokens")){
            int data;
            char ch;
            while ((data = fr.read()) != -1){
                if (Character.isLetter(data)||Character.isDigit(data)){
                    ch = (char) data;
                    if (map.containsKey(ch)){
                        map.put(ch, map.get(ch) + 1);
                    } else {
                        map.put(ch, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, Integer> getWords(){
        Map<String, Integer> map = new TreeMap<>();
        int data;
        char ch;
        StringBuilder sb = new StringBuilder();
        String str;
        try(FileReader fr = new FileReader("wiki.test.tokens")){
            while ((data = fr.read()) != -1){
                if (Character.isLetter(data)||Character.isDigit(data)){
                    ch = (char) data;
                    sb.append(ch);
                } else {
                    str = sb.toString();
                    if (map.containsKey(str)){
                        map.put(str, map.get(str) + 1);
                    } else {
                        map.put(str, 1);
                    }
                    sb = new StringBuilder();
                }
            }
            map.remove("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }
}
