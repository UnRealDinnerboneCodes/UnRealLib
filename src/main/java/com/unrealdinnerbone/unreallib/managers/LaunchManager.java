package com.unrealdinnerbone.unreallib.managers;

import com.beust.jcommander.JCommander;
import com.unrealdinnerbone.unreallib.ReflectionHelper;
import com.unrealdinnerbone.unreallib.api.LaunchParameter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class LaunchManager {

    public static <T extends Class<?>> Map<Class<T>, T> init(String[] args) {
        JCommander.Builder builder = new JCommander.Builder();
        HashMap<Class<T>, T> tHashMap = new HashMap<>();
        ReflectionHelper.getClassWithAnnotation(LaunchParameter.class).forEach(aClass -> {
            T tObject = (T) ReflectionHelper.createInstance(aClass);
            builder.addObject(tObject);
            tHashMap.put((Class<T>) aClass, tObject);
        });
        builder.build().parse(args);
        return tHashMap;
    }
}
