//package com.unrealdinnerbone.unreallib;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@Getter
//@Slf4j
//public class LongCache {
//
//    private final File file;
//    private int count = 0;
//
//    private List<String> longList;
//    private boolean isSaving = false;
//
//    public LongCache(File file) {
//        this.file = file;
//        log.debug(file.getName());
//        Cacha cacha = FileHelper.jsonFromFile(file, JsonUtil.getBasicGson(), Cacha.class);
//        if (cacha == null) {
//            cacha = new Cacha(new ArrayList<>());
//        }
//        this.longList = cacha.getLongList();
//        save();
//    }
//
//    public void save() {
//        if (!isSaving) {
//            isSaving = true;
//            FileHelper.writeStringToFile(JsonUtil.getBasicGson().toJson(new Cacha(longList)), file, false);
//            isSaving = false;
//        }
//    }
//
//    public void add(String value) {
//        longList.add(value);
//        if(++count >= 10) {
//            save();
//            count = 0;
//        }
//    }
//
//    public boolean has(String longg) {
//        return longList.contains(longg);
//    }
//
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    public static class Cacha {
//        private List<String> longList;
//    }
//}
