package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderProcessor {

    private Path path;

    int errCount = 0;

    List<Order> loadList = new ArrayList<>();

    // инициализирует класс, с указанием начальной папки для хранения файлов
    public OrderProcessor(String startPath) {
        this.path = Paths.get(startPath);
    }

    private boolean checkStart(LocalDate start, LocalDateTime fileDateTime) {
        if ((start == null) || (fileDateTime.compareTo(start.atStartOfDay()) >= 0))
            return true;
        else
            return false;
    }

    private boolean checkFinish(LocalDate finish, LocalDateTime fileDateTime) {
        if ((finish == null) || (fileDateTime.compareTo(finish.atStartOfDay()) <= 0))
            return true;
        else
            return false;
    }

    private boolean checkShop(String shopId, String fileShopId) {
        if ((shopId == null) || (shopId.equals(fileShopId)))
            return true;
        else
            return false;
    }

    private boolean checkOrder(LocalDate start, LocalDate finish, String shopId, LocalDateTime fileDateTime, String fileShopId) {
        return checkStart(start, fileDateTime) &&
                checkFinish(finish, fileDateTime) &&
                checkShop(shopId, fileShopId);
    }

    private OrderItem getItem(String itemString) {
        OrderItem orderItem = new OrderItem();
        int index = 1;
        for (String str : itemString.split(",")
        ) {
            if (index == 1) {
                orderItem.googsName = str;
            } else if (index == 2) {
                orderItem.count = Integer.valueOf(str.trim());
            } else {
                orderItem.price = Double.valueOf(str.trim());
            }

            index++;
        }
        return orderItem;
    }

    private double getSum(List<OrderItem> orderItemList) {
        double sum = 0.0;
        for (OrderItem oi : orderItemList
        ) {
            sum += oi.count * oi.price;
        }
        return sum;
    }

    public void addOrder(Path path, LocalDate start, LocalDate finish, String shopId) throws IOException {

        // check shopId & time
        FileTime fileTime = (FileTime) Files.getAttribute(path, "lastModifiedTime");
        LocalDateTime fileDateTime = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());

        String fileShopId = path.getFileName().toString().substring(0, 3);

        if (checkOrder(start, finish, shopId, fileDateTime, fileShopId)) {
            Order order = new Order();
            List<OrderItem> orderItemList = new ArrayList<>();
            // shopId, orderId, customerId
            order.shopId = fileShopId;
            order.orderId = path.getFileName().toString().substring(4, 10);
            order.customerId = path.getFileName().toString().substring(11, 15);
            // datetime
            order.datetime = fileDateTime;
            // items
            List<String> allLines = Files.readAllLines(path);
            for (String str : allLines
            ) {
                orderItemList.add(getItem(str));
            }
            order.items = orderItemList;
            order.sum = getSum(orderItemList);
            loadList.add(order);
        }
    }

    // загружает заказы за указанный диапазон дат, с start до finish, обе даты включительно
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???-??????-????.csv");
        // Метод возвращает количество файлов с ошибками.

        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(path)) {
                    System.out.println(path);
                    addOrder(path, start, finish, shopId);
                } else {
                    errCount++;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
        Collections.sort(loadList);
        return errCount;
    }

    // выдать список заказов в порядке обработки (отсортированные по дате-времени),
    // для заданного магазина. Если shopId == null, то для всех
    public List<Order> process(String shopId) {
        if (shopId == null) {
            return loadList;
        } else {
            List<Order> orders = new ArrayList<>();
            for (Order order : loadList
            ) {
                if (order.shopId.equals(shopId)) {
                    orders.add(order);
                }
            }
            return orders;
        }
    }


    public static void main(String[] args) throws IOException {

        Files.setAttribute(Paths.get("D:\\H17\\processor\\S03-P01X05-0001.csv")
                , "lastModifiedTime"
                , FileTime.from(Instant.parse("2020-12-21T19:34:50.63Z"))
        );

        OrderProcessor processor = new OrderProcessor("D:/H17/processor");
        System.out.println(processor.loadOrders(null //LocalDate.of(2020, 12, 23)
                , LocalDate.of(2020, 12, 23)
                , null)
        );
        for (Order o : processor.process(null)
        ) {
            System.out.println(o);
        }
    }
}
