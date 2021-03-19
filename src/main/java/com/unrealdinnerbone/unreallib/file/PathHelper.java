package com.unrealdinnerbone.unreallib.file;

import com.google.gson.Gson;
import com.unrealdinnerbone.unreallib.MurmurHash;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PathHelper {

    private static final String[] BAD_CHAR = new String[]{",", "!", "|", ":", "?", "'", "*", "<", ">", "+"};

    public static void downloadFile(String url, Path path) throws IOException {
        downloadFile(new URL(url), path);
    }

    public static void downloadFile(URL url, Path file) throws IOException {
        downloadFile(url, file.toFile());
    }

    private static void downloadFile(URL url, File file) throws IOException {
        FileUtils.copyURLToFile(url, file);
    }

    public static String fixFileName(String name) {
        String s = name.replaceAll("\"[^a-zA-Z0-9\\\\.\\\\-]\"", "");
        for (String c : BAD_CHAR) {
            s = s.replace(c, "");
        }
        return s;
    }

    public static <T> T parsePath(Path file, Gson gson, Class<T> tClass) throws IOException {
        return gson.fromJson(Files.readString(file), tClass);
    }

    public static void movePath(Path from, Path to) throws IOException{
        FileUtils.moveFile(from.toFile(), to.toFile());
    }

    public static long getFilesMurmurHash(File file) throws IOException {
        return MurmurHash.murmurHashHash32(MurmurHash.removeBadValuesFromArray(Files.readAllBytes(file.toPath())), 1);
    }

    public static List<Path> getOfPathsInFolder(Path path, Predicate<Path> fileFilter) throws IOException {
        return Files.walk(path).filter(fileFilter).collect(Collectors.toList());
    }
}
