package com.unrealdinnerbone.unreallib.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static long getFilesMurmurHash(Path path) throws IOException {
        return PathHelper.getFilesMurmurHash(path.toFile());
    }

    public static List<Path> getOfPathsInFolder(Path path, Predicate<Path> fileFilter) throws IOException {
        return Files.walk(path).filter(fileFilter).collect(Collectors.toList());
    }

    public static String findExtensions(String fileName) {
        int lastIndex = fileName.indexOf('.');
        return lastIndex == -1 ? "" : "." + fileName.substring(lastIndex + 1);
    }
    public static String findExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        return lastIndex == -1 ? "" : "." + fileName.substring(lastIndex + 1);
    }

    public static void copyFile(Path src, Path dest, Map<String, String> replacements) throws IOException {
        String content = Files.readString(src, StandardCharsets.UTF_8);
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            content = content.replace(entry.getKey(), entry.getValue());
        }
        Files.writeString(dest, content);
    }

    public static void copyFolder(Path src, Path dest, Map<String, String> replacements) throws IOException {
        try (Stream<Path> stream = Files.walk(src)) {
            for (Path source: stream.toList()) {
                Path newFile = dest.resolve(src.relativize(source));
                if(Files.isDirectory(source)) {
                    Files.createDirectories(newFile);
                }else {
                    try {
                        copyFile(src, dest, replacements);
                    }catch (MalformedInputException e) {
                        Files.copy(source, newFile);
                    }
                }
            }
        }
    }

    public void streamToFile(InputStream inputStream, Path path) throws IOException {
        Files.createDirectory(path.getParent());
        Files.createFile(path);
        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
    }
}
