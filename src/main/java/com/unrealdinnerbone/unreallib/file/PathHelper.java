package com.unrealdinnerbone.unreallib.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public class PathHelper {

    public static void downloadFile(String url, Path path) throws IOException {
        downloadFile(new URL(url), path);
    }

    public static void downloadFile(URL url, Path file) throws IOException {
        downloadFile(url, file.toFile());
    }

    private static void downloadFile(URL url, File file) throws IOException {
        FileUtils.copyURLToFile(url, file);
    }
}
