package com.unrealdinnerbone.unreallib.file;

import com.google.common.base.Charsets;
import com.unrealdinnerbone.unreallib.MurmurHash;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
public class FileHelper {

    @SneakyThrows
    private static File createFileIfDoesNotExist(@NonNull File file) {
        if (file.createNewFile()) {
            log.debug("{} created new file", file.getName());
        } else {
            log.debug("{} file already exist not need to create it", file.getName());
        }
        return file;
    }

    @SneakyThrows
    private static File createFolderIfDoesNotExist(@NonNull File fileFolder) {
        if (fileFolder.mkdir()) {
            log.debug("{} created folder", fileFolder.getName());
            return fileFolder;
        } else {
            log.debug("{} folder already exist no need to create it", fileFolder.getName());
        }
        return fileFolder;
    }

    public static File getOrCreateFolder(@NonNull String folderName) {
        return createFolderIfDoesNotExist(new File(folderName));
    }

    public static File getOrCreateFolder(@NonNull File fileBase, @NonNull String folderName) {
        return createFolderIfDoesNotExist(new File(fileBase, folderName));
    }

    public static File getOrCreateFolder(@NonNull String name, @NonNull String folderName) {
        return createFolderIfDoesNotExist(new File(name, folderName));
    }
    public static File getOrCreateFolder(@NonNull File file) {
        return createFolderIfDoesNotExist(file);
    }


    public static File getOrCreateFile(@NonNull File fileName) {
        return createFileIfDoesNotExist(fileName);
    }
    public static File getOrCreateFile(@NonNull String fileName) {
        return createFileIfDoesNotExist(new File(fileName));
    }


    public static File getOrCreateFile(@NonNull File fileBase, @NonNull String fileName) {
        return createFileIfDoesNotExist(new File(fileBase, fileName));
    }

    public static File getOrCreateFile(@NonNull String name, @NonNull String fileName) {
        return createFileIfDoesNotExist(new File(getOrCreateFolder(name), fileName));
    }

    public static File getFile(@NonNull File name, @NonNull String fileName) {
        return new File(name, fileName);
    }


    public static void deleteFile(File file) {
        if (fileExist(file)) {
            file.delete();
        }
    }

    public static boolean isFileEmpty(File fIle) {
        try {
            return new BufferedReader(new FileReader(fIle)).readLine() == null;
        } catch (IOException e) {
            log.error("There was and error creating thing", e);
        }
        return false;
    }

    public static FileWriter createFileWriter(@NonNull File file) {
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            log.error("Error", e);
            return null;
        }
    }

    public static void writeToFileThenClose(@NonNull FileWriter fileWriter, @NonNull String str) {
        try {
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            log.error("Error", e);
        }
    }

    public static URL createURL(@NonNull String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            log.error("Error", e);
        }
        return null;
    }

    public static void writeStringToFile(String string, File file, boolean append) {
        try {
            FileUtils.writeStringToFile(file, string, Charsets.UTF_8, append);
        } catch (IOException e) {
            log.error("Error", e);
        }
    }

    public static FileReader newFileReader(File file) {
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            log.error("Error", e);
        }
        return null;
    }

    public static boolean isFileType(String type, File file) {
        if(type != null) {
            return file.getName().endsWith("." + type);
        }else {
            return true;
        }
    }

    public static List<File> getFilesTypesInFolder(File file, String type) {
        ArrayList<File> files = new ArrayList<>();
        for (File listFile : Objects.requireNonNull(file.listFiles())) {
            if (isFileType(type, listFile)) {
                files.add(listFile);
            }
        }
        return files;
    }

    public static void downloadFile(String url, File file)  {
        downloadFile(createURL(url), file);
    }

    public static void downloadFile(URL url, File file)  {
//        SchedulerService.SCHEDULER_SERVICE.execute(() -> {
            try {
                FileUtils.copyURLToFile(url, file);
            } catch (IOException e) {
                log.error("Error", e);
            }
//        });
    }
    public static boolean fileExist(File file) {
        return file.exists();
    }

    public static ArrayList<String> readAllLinesinFile(File folder, String fileName) {
        return readAllLinesinFile(getOrCreateFile(folder, fileName));
    }

    public static ArrayList<String> readAllLinesinFile(String fileName) {
        return readAllLinesinFile(getOrCreateFile(fileName));
    }

    public static ArrayList<String> readAllLinesinFile(File file) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            log.error("Error", e);
        }
        return arrayList;
    }

    public static void moveFile(File theFile, File file) {
        try {
            FileUtils.moveFile(theFile, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long getFilesMurmurHash(File file) {
        try {
            return MurmurHash.murmurHashHash32(MurmurHash.removeBadValuesFromArray(Files.readAllBytes(file.toPath())), 1);
        } catch (IOException e) {
            log.error("Error", e);
        }
        return -1;
    }
}
