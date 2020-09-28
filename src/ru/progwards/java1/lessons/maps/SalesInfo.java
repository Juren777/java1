package ru.progwards.java1.lessons.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SalesInfo {

    private boolean checkFields(String line){
        int count = 0;
        String field;
        try(Scanner sc = new Scanner(line).useDelimiter("\\s*,\\s*")){
            while (sc.hasNext()){
                field = sc.next();
                if (count == 2){
                    try {
                        int i = Integer.parseInt(field);
                    } catch (NumberFormatException nfe){
                        return false;
                    }
                } else if (count == 3){
                    try {
                        double i = Double.parseDouble(field);
                    } catch (NumberFormatException nfe){
                        return false;
                    }
                }
                count++;
            }
        }
        if (count == 4){
            return true;
        }
        return false;
    }

    public int loadOrders(String fileName){
        int count = 0;
        File file = new File(fileName);
        String line;
        try(Scanner sc = new Scanner(file);
            FileWriter fr = new FileWriter(file.getName())
        ){
            while(sc.hasNextLine()){
                line = sc.nextLine();
                if (checkFields(line)){
                    fr.write(line + "\n");
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Map<String, Double> getGoods(){
        Map<String, Double> map = new TreeMap<>();

        return map;
    }
}
