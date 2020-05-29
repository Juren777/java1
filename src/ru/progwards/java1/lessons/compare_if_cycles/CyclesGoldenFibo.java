package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {

    public static boolean containsDigit(int number, int digit){
        String str =Integer.toString(number);
        char dig =  Character.forDigit(digit, 10);
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == dig){
                System.out.println(dig);
                return true;
            }
        }

        return false;
    }

    public static int fiboNumber(int n){
        if (n == 1 || n == 2){
            return 1;
        }
        int fib = 1;
        int prev = 1;
        int old;
        for (int i = 2; i < n; i++ ){
            old = prev;
            prev = fib;
            fib = old + prev;
        }
        return fib;
    }

    public static boolean isGoldenTriangle(int a, int b, int c){
        double a1 = a, c1 = c;

        return (c1 / a1) > 1.61703 && (c1 / a1) < 1.61903;
    }


    public static void main(String[] args) {

        containsDigit(567,5);
        for (int i = 1; i <= 15; i++){
            System.out.println(fiboNumber(i));
            for (int j = 1; j <= 100; j++){
                if (isGoldenTriangle(fiboNumber(i),j,j)){
                    System.out.println("длинна боковой стороны = " + j + "  основание = " + fiboNumber(i));
                }
            }
        }

    }
}
