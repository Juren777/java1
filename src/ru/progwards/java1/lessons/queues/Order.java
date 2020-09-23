package ru.progwards.java1.lessons.queues;

public class Order implements Comparable<Order>{

    private double sum; // сумма заказа
    private int num; // номер заказа
    private int priority; // класс заказа

    public Order(double sum){
        this.sum = sum;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Order order){
        int c;
        c = Double.compare(priority, order.priority);
        if (c == 0){
            c = Integer.compare(num, order.num);
        }
        return c;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", num=" + num +
                '}';
    }
}
