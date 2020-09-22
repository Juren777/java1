package ru.progwards.java1.lessons.queues;

import org.jetbrains.annotations.NotNull;

public class Order implements Comparable<Order>{

    private double sum; // сумма заказа
    private int num; // номер заказа

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

    @Override
    public int compareTo(@NotNull Order order){
        return Double.compare(num, order.num);
    }

    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", num=" + num +
                '}';
    }
}
