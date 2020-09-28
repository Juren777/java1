package ru.progwards.java1.lessons.maps;

import java.io.*;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SalesInfo {


    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

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
        setFilename(file.getName());
        try(Scanner sc = new Scanner(file);
            FileWriter fr = new FileWriter(file.getName())
        ){
            while(sc.hasNextLine()){
                line = sc.nextLine();
                if (checkFields(line)){
                    fr.write(line + "\n");
                    count++;
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private String getString(String str, int column){
        String ret = null;
        try(Scanner sc = new Scanner(str).useDelimiter("\\s*,\\s*")){
            int i = 0;
            while (sc.hasNext()){
                if (i == column){
                    ret = sc.next();
                }
                sc.next();
                i++;
            }
        }
        return ret;
    }
    private Double getDouble(String str){
        Double ret = null;
        try(Scanner sc = new Scanner(str).useDelimiter("\\s*,\\s*")){
            int i = 0;
            while (sc.hasNext()){
                if (i == 3){
                    ret = Double.parseDouble(sc.next());
                    return ret;
                }
                sc.next();
                i++;
            }
        }
        return ret;
    }
    private Integer getInteger(String str){
        Integer ret = null;
        try(Scanner sc = new Scanner(str).useDelimiter("\\s*,\\s*")){
            int i = 0;
            while (sc.hasNext()){
                if (i == 2){
                    ret = Integer.parseInt(sc.next());
                    return ret;
                }
                sc.next();
                i++;
            }
        }
        return ret;
    }
    public Map<String, Double> getGoods(){
        Map<String, Double> map = new TreeMap<>();
        String str;
        String key;
        Double value;
        try(FileReader fr = new FileReader(getFilename());
        Scanner scanner = new Scanner(fr)){
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                key = getString(str, 1);
                value = getDouble(str);
                if (map.containsKey(key)){
                    map.put(key, map.get(key) + value);
                } else {
                    map.put(key, value);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> map = new TreeMap<>();
        String str;
        String key;
        Double value;
        Integer count;

        try(FileReader fr = new FileReader(getFilename());
            Scanner scanner = new Scanner(fr)){
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                key = getString(str, 0);
                value = getDouble(str);
                count = getInteger(str);
                if (map.containsKey(key)){
                    map.put(key, new AbstractMap.SimpleEntry<>(map.get(key).getKey() + value, map.get(key).getValue() + count));
                } else {
                    map.put(key, new AbstractMap.SimpleEntry<>(value, count));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
