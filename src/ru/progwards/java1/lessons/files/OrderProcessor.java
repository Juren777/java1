package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.*;

public class OrderProcessor {

    private Path path;

    int errCount = 0;

    List<Order> loadList = new ArrayList<>();

    // инициализирует класс, с указанием начальной папки для хранения файлов
    public OrderProcessor(String startPath) {
        this.path = Paths.get(startPath);
    }

    private boolean checkStart(LocalDate start, LocalDateTime fileDateTime) {
        if ((start == null) || (fileDateTime.isAfter(start.atStartOfDay())))
            return true;
        else
            return false;
    }

    private boolean checkFinish(LocalDate finish, LocalDateTime fileDateTime) {
        if ((finish == null) || (fileDateTime.compareTo(finish.atStartOfDay().plusDays(1)) < 0))
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
        if (orderItem.count != 0)
            return orderItem;
        else
            return null;
    }

    private double getSum(List<OrderItem> orderItemList) {
        double sum = 0.0;
        for (OrderItem oi : orderItemList
        ) {
            sum += oi.count * oi.price;
        }
        return sum;
    }

    public void addOrder(Path path, LocalDate start, LocalDate finish, String shopId) {

        // check shopId & time
        LocalDateTime fileDateTime = null;
        try {
            fileDateTime = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneId.systemDefault());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String fileShopId = path.getFileName().toString().substring(0, 3);

        Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();
        // shopId, orderId, customerId
        order.shopId = fileShopId;
        order.orderId = path.getFileName().toString().substring(4, 10);
        order.customerId = path.getFileName().toString().substring(11, 15);
        // datetime
        order.datetime = fileDateTime;
        // items
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(path);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        for (String str : allLines
        ) {
            if (getItem(str) != null)
                orderItemList.add(getItem(str));
        }
        Collections.sort(orderItemList);
        order.items = orderItemList;
        order.sum = getSum(orderItemList);
        if (order.items.size() != 0)
            loadList.add(order);
    }


    // загружает заказы за указанный диапазон дат, с start до finish, обе даты включительно
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???-??????-????.csv");
        // Метод возвращает количество файлов с ошибками.

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    LocalDateTime fileDateTime = null;
                    try {
                        fileDateTime = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneId.systemDefault());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    String fileShopId = path.getFileName().toString().substring(0, 3);
                    if (checkOrder(start, finish, shopId, fileDateTime, fileShopId)) {
                        if (pathMatcher.matches(path)) {
                            //System.out.println(path);
                            addOrder(path, start, finish, shopId);
                        } else {
                            errCount++;
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
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

    /* - выдать информацию по объему продаж по магазинам (отсортированную по ключам):
       String - shopId, double - сумма стоимости всех проданных товаров в этом магазине
    */
    public Map<String, Double> statisticsByShop() {
        Map<String, Double> map = new TreeMap<>();
        for (Order order : loadList
        ) {
            if (map.get(order.shopId) == null) {
                map.put(order.shopId, order.sum);
            } else {
                map.replace(order.shopId, map.get(order.shopId) + order.sum);
            }
        }
        return map;
    }

    /* - выдать информацию по объему продаж по товарам (отсортированную по ключам):
       String - goodsName, double - сумма стоимости всех проданных товаров этого наименования
    */
    public Map<String, Double> statisticsByGoods() {
        Map<String, Double> map = new TreeMap<>();
        List<OrderItem> items = new ArrayList<>();
        for (Order order : loadList
        ) {
            for (OrderItem item : order.items
            ) {
                items.add(item);
            }
        }
        for (OrderItem orderItem : items
        ) {
            if (map.get(orderItem.googsName) == null) {
                map.put(orderItem.googsName, orderItem.count * orderItem.price);
            } else {
                map.replace(orderItem.googsName, map.get(orderItem.googsName) + orderItem.count * orderItem.price);
            }
        }
        return map;
    }

    /* - выдать информацию по объему продаж по дням (отсортированную по ключам):
       LocalDate - конкретный день, double - сумма стоимости всех проданных товаров в этот день
    */
    public Map<LocalDate, Double> statisticsByDay() {
        Map<LocalDate, Double> map = new TreeMap<>();
        LocalDate localDate;
        for (Order order : loadList
        ) {
            localDate = LocalDate.from(order.datetime);
            if (map.get(localDate) == null) {
                map.put(localDate, order.sum);
            } else {
                map.put(localDate, map.get(localDate) + order.sum);
            }
        }
        return map;
    }


    public static void main(String[] args) throws IOException {

        // year, month, dayOfMonth, hour, minute, second 2020-01-01T13:00
        LocalDateTime newLocalDateTime = LocalDateTime.of(2020, 1, 10, 16, 15, 15);
        Instant instant = newLocalDateTime.toInstant(ZoneOffset.ofHours(3));
        Files.setLastModifiedTime(Paths.get("D:/H17/processor/2/S02-P01X02-0003.csv")
                , FileTime.from(instant)
        );

        OrderProcessor processor = new OrderProcessor("D:/H17/processor");
        System.out.println(processor.loadOrders(LocalDate.of(2020, Month.JANUARY, 11), null, null)
        );
        for (Order o : processor.process(null)
        ) {
            System.out.println(o);
        }
        System.out.println(processor.statisticsByShop());
        System.out.println(processor.statisticsByGoods());
        System.out.println(processor.statisticsByDay());
    }
}
