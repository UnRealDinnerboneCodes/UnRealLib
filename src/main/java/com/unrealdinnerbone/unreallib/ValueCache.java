//package com.unrealdinnerbone.unreallib;
//
//import com.unrealdinnerbone.unreallib.file.FileHelper;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Slf4j
//public class ValueCache<T> {
//
//    private final File file;
//    private int count = 0;
//
//    private List<T> longList;
//    private boolean isSaving = false;
//
//    public ValueCache(File file) {
//        this.file = file;
//        log.debug(file.getName());
//        Cacha<T> cacha = FileHelper.jsonFromFile(file, JsonUtil.getBasicGson(), Cacha.class);
//        if (cacha == null) {
//            cacha = new Cacha<>(new ArrayList<>());
//        }
//        this.longList = cacha.getValues();
//        save();
//    }
//
//    public void save() {
//        if (!isSaving) {
//            isSaving = true;
//            FileHelper.writeStringToFile(JsonUtil.getBasicGson().toJson(new Cacha<>(longList)), file, false);
//            isSaving = false;
//        }
//    }
//
//    public void add(T value) {
//        longList.add(value);
//        save();
//    }
//    public List<T> getList() {
//        return longList;
//    }
//
//    public boolean has(T longg) {
//        return longList.contains(longg);
//    }
//
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    public static class Cacha<T> {
//        private List<T> values;
//    }
//}
