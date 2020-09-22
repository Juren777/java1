package ru.progwards.java1.lessons.queues;

public class Calculate {

    public static double calculation1(){
        StackCalc sc = new StackCalc();
        sc.push(2.2);
        sc.push(3);
        sc.push(12.1);

        //-------------------------------------
        sc.add();
        sc.mul();
        return sc.pop();
    }

    public static double calculation2(){
        StackCalc sc = new StackCalc();
        sc.push(87);
        sc.push(2);
        sc.push(13.001);
        sc.push(9.2);

        //-------------------------------------
        sc.sub(); // 13.001 - 9.2
        sc.mul(); // *2
        sc.add(); // +87 = 94.602
        //-------------------------------------
        sc.push(19);
        sc.push(3.33);
        //-------------------------------------
        sc.sub(); // 19 - 3.33
        sc.mul(); // 94.602 * 15.67 = 1482.41334
        //-------------------------------------
        sc.push(737.22);
        sc.push(24);
        //-------------------------------------
        sc.add(); // 761.22
        //-------------------------------------
        sc.push(55.6);
        sc.push(12.1);
        //-------------------------------------
        sc.sub(); // 43.5
        sc.div(); // 761.22/43.5 = 17.499310344827588
        sc.add(); // 17.499310344827588 + 1482.41334
        return sc.pop();
    }

    public static void main(String[] args) {
        System.out.println(calculation2());

    }

}
