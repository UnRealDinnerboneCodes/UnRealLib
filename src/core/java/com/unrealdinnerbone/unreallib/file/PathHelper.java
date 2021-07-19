package com.unrealdinnerbone.unreallib.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PathHelper {

    private static final String[] BAD_CHAR = new String[]{",", "!", "|", ":", "?", "'", "*", "<", ">", "+"};

    public static Path tryGetOrCreateFolder(Path path) throws IOException {
        return !Files.exists(path) ? Files.createDirectory(path) : path;
    }

    public static Path tryGetOrCreateFile(Path path) throws IOException {
        return !Files.exists(path) ? Files.createFile(path) : path;
    }

    public static Optional<Path> getOrCreateFile(Path path) {
        try {
            return Optional.ofNullable(tryGetOrCreateFile(path));
        } catch(IOException e) {
            return Optional.empty();
        }
    }

    public static void downloadFile(String url, Path path) throws IOException {
        downloadFile(new URL(url), path);
    }

    public static void downloadFile(URL url, Path file) throws IOException {
        Files.copy(url.openStream(), file);
    }

    public static String fixFileName(String name) {
        String s = name.replaceAll("\"[^a-zA-Z0-9\\\\.\\\\-]\"", "");
        for (String c : BAD_CHAR) {
            s = s.replace(c, "");
        }
        return s;
    }

    public static long getFilesMurmurHash(File file) throws IOException {
        return MurmurHash.murmurHashHash32(MurmurHash.removeBadValuesFromArray(Files.readAllBytes(file.toPath())), 1);
    }

    public static List<Path> getOfPathsInFolder(Path path, Predicate<Path> fileFilter) throws IOException {
        return Files.walk(path).filter(fileFilter).collect(Collectors.toList());
    }

    public void streamToFile(InputStream inputStream, Path path) throws IOException {
        Files.createDirectory(path.getParent());
        Files.createFile(path);
        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
    }
}
