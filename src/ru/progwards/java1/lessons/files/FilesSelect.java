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
            sb.append(ch);
            if (sb.toString().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /*Нужно просмотреть содержимое всех файлов, с расширением txt,
     содержащихся в каталоге inFolder с подкаталогами, и если файл содержит ключевое слово из коллекции keys,
     то скопировать его в подпапку с соответствующим именем, этого элемента keys,
     все подпапки должны находиться в outFolder.
     */
    public void selectFiles(String inFolder, String outFolder, List<String> keys) {

        Path startPath = Paths.get(inFolder);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");

        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<>() {

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) {
                        String fileSource = null;
                        try {
                            fileSource = Files.readString(path);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        for (String key : keys
                        ) {
                            if (checkFile(fileSource, key)) {
                                Path outPath = Paths.get(outFolder + "/" + key);
                                // check directory
                                if (!Files.exists(outPath)) {
                                    try {
                                        Files.createDirectory(outPath);
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }
                                }
                                Path outFile = Paths.get(outPath + "/" + path.getFileName());
                                // check file
                                if (!Files.exists(outFile)) {
                                    try {
                                        Files.copy(path, outFile);
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }
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
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {
        FilesSelect filesSelect = new FilesSelect();
        List<String> list = new ArrayList<>();
        list.add("select");
        list.add("insert");
        filesSelect.selectFiles("D:/new", "D:/drop_csp", list);
    }
}
