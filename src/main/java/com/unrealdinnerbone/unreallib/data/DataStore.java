package com.unrealdinnerbone.unreallib.cache;

import com.unrealdinnerbone.unreallib.file.FileHelper;

import java.io.File;
import java.util.List;

public class Cache<T>
{
    private final List<String> cahceList;
    private final File cacheFile;

    public Cache(File file) {
        this.cahceList = FileHelper.readAllLinesinFile(file);
        this.cacheFile = file;
    }

    public void add(T t) {
        if(!contains(t)) {
            FileHelper.writeStringToFile(t.toString() + "\n", cacheFile, true);
            cahceList.add(t.toString());
        }
    }

    public boolean contains(T t) {
        return cahceList.contains(t.toString());
    }
}
