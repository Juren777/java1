package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FilesSelect {


    public boolean checkFile(String fileSource, String key) {
        StringBuilder sb = new StringBuilder();
        for (char ch : fileSource.toCharArray()
        ) {
            if (Character.isLetter(ch)) {
                sb.append(ch);
            } else {
                if (sb.toString().equals(key)) {
                    return true;
                }
                sb = new StringBuilder();
            }
        }
        return false;
    }

    /*Нужно просмотреть содержимое всех файлов, с расширением txt,
     содержащихся в каталоге inFolder с подкаталогами, и если файл содержит ключевое слово из коллекции keys,
     то скопировать его в подпапку с соответствующим именем, этого элемента keys,
     все подпапки должны находиться в outFolder.
     */
    public void selectFiles(String inFolder, String outFolder, List<String> keys) throws IOException {

        Path startPath = Paths.get(inFolder);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.sql");

        Files.walkFileTree(startPath, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(path)) {
                    String fileSource = Files.readString(path);
                    for (String key: keys
                         ) {
                        if (checkFile(fileSource, key)) {
                            Path outPath = Paths.get(outFolder + "/" + key );
                            if (!Files.exists(outPath)){
                                Files.createDirectory(outPath);
                            }
                            Path outFile = Paths.get(outPath + "/" + path.getFileName());
                            if (!Files.exists(outFile)){
                                Files.copy(path, outFile);
                            }
                        }
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });

    }


    public static void main(String[] args) throws IOException {
        FilesSelect filesSelect = new FilesSelect();
        List<String> list = new ArrayList<>();
        list.add("select");
        list.add("insert");
        filesSelect.selectFiles("D:/new", "D:/drop_csp", list);
    }
}
