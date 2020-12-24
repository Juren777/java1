package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {

    private Path path;

    int errCount = 0;

    List<Order> loadList = new ArrayList<>();

    // инициализирует класс, с указанием начальной папки для хранения файлов
    public OrderProcessor(String startPath) {
        this.path = Paths.get(startPath);
    }

    public OrderItem getItem(String itemString){
        OrderItem orderItem = new OrderItem();
        int index = 1;
        for (String str: itemString.split(",")
             ) {
            if (index++ == 1){
                orderItem.googsName = str;
            }

        }

        return orderItem;
    }

    public void addOrder(Path path) throws IOException {
        Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();
        List<String> allLines = Files.readAllLines(path);
        for (String str: allLines
             ) {
            orderItemList.add(getItem(str));
        }
        order.items = orderItemList;
        loadList.add(order);
    }



    // загружает заказы за указанный диапазон дат, с start до finish, обе даты включительно
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???-??????-????.csv");
        // Метод возвращает количество файлов с ошибками.

        Files.walkFileTree(path, new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {

                if (pathMatcher.matches(path)){
                    System.out.println(path);
                    addOrder(path);
                } else {
                    errCount++;
                }

                return FileVisitResult.CONTINUE;
            }
        });
        return errCount;
    }

    // выдать список заказов в порядке обработки (отсортированные по дате-времени),
    // для заданного магазина. Если shopId == null, то для всех
    public List<Order> process(String shopId) {

        List<Order> orders = new ArrayList<>();

        return orders;
    }


    public static void main(String[] args) throws IOException {
        OrderProcessor processor = new OrderProcessor("D:/H17/processor");
        System.out.println(processor.loadOrders(null, null, null));
        System.out.println(processor.loadList);
    }
}
