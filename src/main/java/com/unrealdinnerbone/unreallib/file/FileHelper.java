package com.unrealdinnerbone.unreallib.file;

import com.google.gson.Gson;
import com.unrealdinnerbone.unreallib.MurmurHash;
import com.unrealdinnerbone.unreallib.web.HttpUtils;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public class FileHelper {

    private static final FileFilter FOLDER_FILTER = File::isDirectory;

    private static File createFileIfDoesNotExist(@NonNull File file) {
        try {
            if (file.createNewFile()) {
                log.debug("{} created new file", file.getName());
            } else {
                log.debug("{} file already exist not need to create it", file.getName());
            }
        } catch (IOException e) {
            log.debug("There was and error while trying to create the file {}", file.getName());
        }
        return file;
    }

    private static File createFolderIfDoesNotExist(@NonNull File fileFolder) {
        if (fileFolder.mkdir()) {
            log.debug("{} created folder", fileFolder.getName());
            return fileFolder;
        } else {
            log.debug("{} folder already exist no need to create it", fileFolder.getName());
        }
        return fileFolder;
    }

    public static void deleteFolder(File file) {
        try {
            log.debug("Deleting folder {}", file.getName());
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            log.error("There was and error while trying to delete a folder", e);
        }
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


    public static File getOrCreateFile(@NonNull File fileBase, @NonNull String fileName, @NonNull FileType fileType) {
        return createFileIfDoesNotExist(new File(fileBase, fileName + "." + fileType.name().toLowerCase()));
    }

    public static File getOrCreateFile(@NonNull String name, @NonNull String fileName, @NonNull FileType fileType) {
        return createFileIfDoesNotExist(new File(getOrCreateFolder(name), fileName + "." + fileType.name().toLowerCase()));
    }

    public static File getFile(@NonNull File name, @NonNull String fileName) {
        return new File(name, fileName);
    }

    private static final String[] BAD_CHAR = new String[]{",", "!", "|", ":", "?", "'", "*"};


    public static String fixFileName(String name) {
        String s = name.replaceAll("\"[^a-zA-Z0-9\\\\.\\\\-]\"", "");
        for (String c : BAD_CHAR) {
            s = s.replace(c, "");
        }
        return s;
    }

    public static <T> T jsonFromFile(File file, Gson gson, Class<T> tClass) {
        FileReader fileReader = newFileReader(file);
        T t = gson.fromJson(fileReader, tClass);
      closeFileReader(fileReader);
        return t;
    }

    public static <T> T jsonFromString(String string, Gson gson, Class<T> tClass) {
        return gson.fromJson(string, tClass);
    }



    public static void closeFileReader(FileReader fileReader) {
        try {
            fileReader.close();
        } catch (IOException e) {
            log.error("There was and error closeing the file reader", e);
        }
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
            FileUtils.writeStringToFile(file, string, StandardCharsets.UTF_8, append);
        } catch (IOException e) {
            log.error("Error", e);
        }
    }

    public static FileReader newFileReader(File file) {
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            log.error("There was and error while trying to create the file reader", e);
        }
        return null;
    }

    public static boolean isFileType(String type, File file) {
        if (type != null) {
            return file.getName().endsWith("." + type);
        } else {
            return true;
        }
    }

    public static void downloadFile(String url, File file) throws IOException {
        downloadFile(createURL(url), file);
    }


    public static void downloadFile(URL url, File file) throws IOException {
        FileUtils.copyURLToFile(url, file);
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

    public static File[] getSubFiles(File file) {
        return file.isDirectory() ? file.listFiles() : new File[]{};
    }

    public static List<File> findFilesInFolder(File file, FileFilter fileFilter, boolean doResuve) {
        List<File> files = new ArrayList<>();
        if(file.isDirectory()) {
            if(doResuve) {
                for (File file1 : file.listFiles(FOLDER_FILTER)) {
                    files.addAll(findFilesInFolder(file1, fileFilter, doResuve));
                }
            }
            Collections.addAll(files, file.listFiles(fileFilter));
            return files;
        }else {
            log.error("Not a Dir ");
            return files;
        }
    }

    public static String inputToSrring(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFileDate(File file1, long formatTime) {
        if(!file1.setLastModified(formatTime)) {
            log.error("There was and error while setting the date on the file");
        }
    }

    public enum FileType {
        JSON;
    }
}
