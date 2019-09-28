//package com.unrealdinnerbone.unreallib.config;
//
//import com.electronwill.nightconfig.core.file.FileConfig;
//import com.electronwill.nightconfig.json.JsonFormat;
//import com.unrealdinnerbone.unreallib.ReflectionHelper;
//import com.unrealdinnerbone.unreallib.file.FileHelper;
//import lombok.SneakyThrows;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Set;
//
//public class ConfigManager {
//
//    public static void init() {
//        Set<Class<?>> configClasses = ReflectionHelper.getClassWithAnnotation(Config.class);
//        configClasses.forEach(ConfigManager::loadConfig);
//    }
//
//    public static void loadConfig(Class<?> configClass) {
//        if (configClass.isAnnotationPresent(Config.class)) {
//            Config configAnnotation = configClass.getAnnotation(Config.class);
//            File configFolder = FileHelper.getOrCreateFolder(configAnnotation.folderName());
//            loadConfig(configClass, configFolder, configAnnotation.value());
//        }
//    }
//
//    public static void loadConfig(Class clazz, File configFolder, String fileName) {
//        File configFile = FileHelper.getOrCreateFile(configFolder, fileName + ".json");
//        if(FileHelper.isFileEmpty(configFile)) {
//            try {
//                JsonFormat.fancyInstance().initEmptyFile(configFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        FileConfig fileConfig = FileConfig.of(configFile, JsonFormat.fancyInstance());
//        fileConfig.load();
//        Arrays.stream(clazz.getDeclaredFields()).filter(field -> !field.isAccessible()).forEach(field -> {
//            field.setAccessible(true);
//            if (!fileConfig.getOptional(field.getName()).isPresent()) {
//                try {
//                    fileConfig.set(field.getName(), field.get(clazz));
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//            Object value = fileConfig.get(field.getName());
//            try {
//                field.set(clazz, value);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        });
//        fileConfig.save();
//    }
//
//}
