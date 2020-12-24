package ru.progwards.java1.lessons.files;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;


/*
    AAA-999999-ZZZZ.csv
    AAA - обязательные 3 символа shopId - идентификатор магазина
    999999 - обязательные 6 символов orderId - номер заказа
    ZZZZ - обязательные 4 символа customerId - идентификатор покупателя
*/
public class Order implements Comparable<Order>{

    public String shopId; // идентификатор магазина

    public String orderId; // идентификатор заказа

    public String customerId; // идентификатор покупателя

    public LocalDateTime datetime; // дата-время заказа (из атрибутов файла - дата последнего изменения)

    public List<OrderItem> items; // список позиций в заказе, отсортированный по наименованию товара

    public double sum; // сумма стоимости всех позиций в заказе

    @Override
    public String toString() {
        return "Order{" +
                "shopId='" + shopId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", datetime=" + datetime +
                ", items=" + items +
                ", sum=" + sum +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        return this.datetime.compareTo(o.datetime);
    }
}
