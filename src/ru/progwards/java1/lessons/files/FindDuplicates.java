package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FindDuplicates {
    // В заданном каталоге и его подкаталогах найти файлы,
    // точно совпадающие по названию (и расширению), дате-времени последнего изменения, размеру и по содержимому.
    // результат - список, содержащий списки строк с именами и полными путями совпадающих файлов


    class DupFile {
        String fileName;
        FileTime fileTime;
        Long fileSize;
        String fileSource;
        Path filePath;
        Integer fileCount;

        public DupFile(String fileName, FileTime fileTime, Long fileSize, String fileSource, Path filePath) {
            this.fileName = fileName;
            this.fileTime = fileTime;
            this.fileSize = fileSize;
            this.fileSource = fileSource;
            this.filePath = filePath;
            this.fileCount = 1;
        }


        @Override
        public String toString() {
            return "DupFile{" +
                    "fileName=" + fileName +
                    ", fileTime=" + fileTime +
                    ", fileSize=" + fileSize +
                    ", fileSource='" + fileSource +
                    ", filePath=" + filePath +
                    ", fileCount=" + fileCount + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DupFile)) return false;
            DupFile dupFile = (DupFile) o;
            return Objects.equals(fileName, dupFile.fileName) && Objects.equals(fileTime, dupFile.fileTime) && Objects.equals(fileSize, dupFile.fileSize) && Objects.equals(fileSource, dupFile.fileSource);
        }
    }

    public List<List<String>> findDuplicates(String startPath){

        List<String> stringList = new ArrayList<>();
        List<List<String>> lists = new ArrayList<>();
        List<DupFile> dupFiles = new ArrayList<>();
        Path path = Paths.get(startPath);

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs){
                    DupFile dupFile = null;
                    try {
                        dupFile = new DupFile(path.getFileName().toString()
                                , (FileTime) Files.getAttribute(path, "lastModifiedTime")
                                , (Long) Files.getAttribute(path, "size")
                                , Files.readString(path)//new String(Files.readAllBytes(path))
                                , path
                        );
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    for (DupFile df : dupFiles
                    ) {
                        if (df.equals(dupFile)) {
                            df.fileCount += 1;
                            dupFile.fileCount = df.fileCount;
                        }
                    }
                    dupFiles.add(dupFile);
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
        for (DupFile df: dupFiles
             ) {
            if (df.fileCount > 1){
                stringList.add(df.fileName);
                stringList.add(df.filePath.toString());
                lists.add(new ArrayList<>(stringList));
                stringList.clear();
            }
        }
        return lists;
    }

    public static void main(String[] args) throws IOException {
        FindDuplicates findDuplicates = new FindDuplicates();
        List<List<String>> lists = findDuplicates.findDuplicates("D:\\new");
        for (List<String> ls : lists
        ) {
            System.out.println(ls);
        }

    }
}
