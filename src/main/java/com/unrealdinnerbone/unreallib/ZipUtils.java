package com.unrealdinnerbone.unreallib;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static void pack(Path output, Path... source) throws IOException {
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(output))) {
            for (Path rootPath : source) {
                try (Stream<Path> paths = Files.walk(rootPath)) {
                    for (Path path : paths.toList()) {
                        if (!Files.isDirectory(path)) {
                            zs.putNextEntry(new ZipEntry(rootPath.getFileName() + File.separator + rootPath.relativize(path)));
                            Files.copy(path, zs);
                            zs.closeEntry();
                        }
                    }
                }
            }
        }
    }
}

