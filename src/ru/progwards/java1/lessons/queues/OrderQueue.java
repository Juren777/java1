package ru.progwards.java1.lessons.queues;

import java.util.PriorityQueue;

public class OrderQueue {

    private PriorityQueue<Order> orderQueue;
    private int num;

    public OrderQueue() {
        this.orderQueue = new PriorityQueue<>();
        this.num = 1;
    }

    private void addNum(){
        num++;
    }

    public void add(Order order) {
        double sum = order.getSum();
        if ((sum > 0) && (sum < 10_000)){
            order.setPriority(3);
            order.setNum(num);
            addNum();
        }
        else if ((sum >= 10_000) && (sum <= 20_000)) {
            order.setPriority(2);
            order.setNum(num);
            addNum();
        } else {
            order.setPriority(1);
            order.setNum(num);
            addNum();
        }
        orderQueue.add(order);
    }

    public Order get() {
        try {
            return orderQueue.poll();
        } catch (NullPointerException e) {
            return null;
        }

    }

    public static void main(String[] args) {
        OrderQueue oq = new OrderQueue();

        oq.add(new Order(447.0));
        oq.add(new Order(25325.0));
        oq.add(new Order(1030.0));
        oq.add(new Order(11046.0));
        oq.add(new Order(15612.0));
        oq.add(new Order(4971.0));
        oq.add(new Order(17881.0));
        oq.add(new Order(22234.0));
        oq.add(new Order(8008.0));
        oq.add(new Order(17336.0));
        oq.add(new Order(8951.0));
        oq.add(new Order(20612.0));
        oq.add(new Order(5051.0));
        oq.add(new Order(17731.0));
        oq.add(new Order(4733.0));
        oq.add(new Order(14442.0));
        oq.add(new Order(22788.0));
        oq.add(new Order(9597.0));
        oq.add(new Order(6239.0));
        oq.add(new Order(13432.0));
        oq.add(new Order(13432.0));
        oq.add(new Order(8646.0));
        oq.add(new Order(7168.0));
        oq.add(new Order(27432.0));


        Order order;
        while ((order = oq.get()) != null) {
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