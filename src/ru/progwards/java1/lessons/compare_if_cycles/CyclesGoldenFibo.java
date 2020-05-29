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
        double div;

        if (a == b){
            div = (double)a/(double)c;
        }else if (b == c){
            div = (double)b/(double)a;
        }else {
            div = (double)a/(double)b;
        }
//        System.out.println(div);

        return div > 1.61703 && div < 1.61903;
    }


    public static void main(String[] args) {
//        System.out.println(isGoldenTriangle(55,55,34));
//        System.out.println(isGoldenTriangle(34,55,55));
//        System.out.println(isGoldenTriangle(55,34,55));
        //containsDigit(567,5);
        for (int i = 1; i <= 15; i++){
            System.out.println(fiboNumber(i));
            for (int j = 1; j <= 100; j++){
                if (isGoldenTriangle(j,j,fiboNumber(i))){
                    System.out.println("  основание = " + fiboNumber(i) + "  длинна боковой стороны = " + j);
                }
            }
        }

    }
}
