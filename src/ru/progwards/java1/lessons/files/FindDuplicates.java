package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {
    // В заданном каталоге и его подкаталогах найти файлы,
    // точно совпадающие по названию (и расширению), дате-времени последнего изменения, размеру и по содержимому.
    // результат - список, содержащий списки строк с именами и полными путями совпадающих файлов
    public List<List<String>> findDuplicates(String startPath) throws IOException {

        Path path = Paths.get(startPath);

        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                List<String> stringList = new ArrayList<>();
                stringList.add(path.getFileName().toString());
                stringList.add(path.toString());
                System.out.println(stringList);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });

        return null;
    }

    public static void main(String[] args) throws IOException {
        FindDuplicates findDuplicates = new FindDuplicates();
        findDuplicates.findDuplicates("/home/yuriyr/dumps");
    }
}
