package ru.progwards.java1.lessons.queues;

import java.util.PriorityQueue;

public class OrderQueue {

    private PriorityQueue<Order> orderQueue;

    public OrderQueue(){
        this.orderQueue = new PriorityQueue<>();
    }

    public void add(Order order){
        double sum = order.getSum();
        if ((sum > 0) && (sum < 10_000))
            order.setNum(3);
        else if ((sum >= 10_000) && (sum <= 20_000)){
            order.setNum(2);
        } else {
            order.setNum(1);
        }
        orderQueue.add(order);
    }

    public Order get(){

        return orderQueue.poll();
    }

    public static void main(String[] args) {
        OrderQueue oq = new OrderQueue();
        oq.add(new Order(5000));
        oq.add(new Order(52200));
        oq.add(new Order(12000));
        oq.add(new Order(15000));
        oq.add(new Order(100000));
        oq.add(new Order(98000));

        Order order;
        while ((order = oq.get()) != null){
            System.out.println(order);

        }

    }

}

/*2.7 Создать метод, public void add(Order order), размещающий заказы в очередь с приоритетом, разбивая их по 3-м классам
3 - заказы до 10000 руб включительно
2 - заказы от 10000 до 20000 руб включительно
1 - заказы от 20000 руб

2.8 Создать метод, public Order get(), возвращающий первый заказ в очереди для обслуживания. Вначале обслуживаются заказы класса 1, потом 2, потом 3. Внутри каждого класса заказы должны обслуживаться в порядке поступления (по номеру заказа). Метод не выбрасывает исключения, возвращает null в случае пустой очереди.

Продумать, и, при необходимости, добавить в классы нужные методы и свойства, для того, чтобы реализовать эту задачу.
 */