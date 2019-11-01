package com.unrealdinnerbone.unreallib.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipFile;

@Slf4j
public class ZipUtils {

    public static String decompressBz2(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BZip2CompressorInputStream cis = new BZip2CompressorInputStream(fileInputStream, true);
            String ret = IOUtils.toString(cis, StandardCharsets.UTF_8);
            cis.close();
            return ret;
        } catch (IOException e) {
            log.error("There was and error while trying to unzip the Bz2 {}", file.getName());
            log.error("Error", e);
        }
        return null;
    }

    public static InputStream getFileFromZip(File theZip, String file) {
        try {
            ZipFile zipFile = new ZipFile(theZip);
            return zipFile.getInputStream(zipFile.getEntry(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
