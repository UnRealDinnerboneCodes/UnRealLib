package com.unrealdinnerbone.unreallib.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipFile;

@Slf4j
public class ZipUtils {

    public static InputStream getFileFromZip(File theZip, String file) {
        try {
            ZipFile zipFile = new ZipFile(theZip);
            return zipFile.getInputStream(zipFile.getEntry(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
