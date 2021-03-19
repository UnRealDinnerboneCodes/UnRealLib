package com.unrealdinnerbone.unreallib.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;

public class ZipUtils {

    public static InputStream getFileFromZip(File theZip, String file) throws IOException {
        ZipFile zipFile = new ZipFile(theZip);
        return zipFile.getInputStream(zipFile.getEntry(file));
    }
}
