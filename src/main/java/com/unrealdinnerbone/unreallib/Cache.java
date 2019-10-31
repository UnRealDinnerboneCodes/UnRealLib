package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.file.FileHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;

@Getter
@Slf4j
public class Cache {

    private final File file;
    private int count = 0;

    private HashMap<Integer, String> map;

    private boolean isSaving = false;

    public Cache(File file) {
        this.file = file;
        log.debug(file.getName());
        Cacha cacha = FileHelper.jsonFromFile(file, JsonUtil.getBasicGson(), Cacha.class);
        if (cacha == null) {
            cacha = new Cacha(new HashMap<>());
        }
        this.map = cacha.getMap();
        save();
    }

    public void save() {
        if (!isSaving) {
            isSaving = true;
            FileHelper.writeStringToFile(JsonUtil.getBasicGson().toJson(new Cacha(map)), file, false);
            isSaving = false;
        }
    }

    public void add(Integer integer, String s) {
        map.put(integer, s);
        if(++count >= 10) {
            save();
            count = 0;
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Cacha {
        private HashMap<Integer, String> map;
    }
}
