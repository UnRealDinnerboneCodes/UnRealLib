package com.unrealdinnerbone.unreallib.data;

import com.unrealdinnerbone.unreallib.file.FileHelper;

import java.io.File;
import java.util.List;

public class DataStore<T>
{
    private final List<String> cahceList;
    private final File cacheFile;

    public DataStore(File file) {
        this.cahceList = FileHelper.readAllLinesinFile(file);
        this.cacheFile = file;
    }

    public void add(T t) {
        if(!contains(t)) {
            FileHelper.writeStringToFile(t.toString() + "\n", cacheFile, true);
            cahceList.add(t.toString());
        }
    }

    public void remove(T t) {
       if(contains(t)) {
           cahceList.remove(t.toString());
           FileHelper.writeStringToFile("", cacheFile, false);
           cahceList.forEach(s -> FileHelper.writeStringToFile(s + "\n", cacheFile, true));
       }
    }

    public List<String> getList() {
        return cahceList;
    }

    public boolean contains(T t) {
        return cahceList.contains(t.toString());
    }
}
