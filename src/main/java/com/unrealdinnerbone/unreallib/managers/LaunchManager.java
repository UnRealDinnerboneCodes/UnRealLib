package com.unrealdinnerbone.unreallib.managers;

import com.beust.jcommander.JCommander;
import com.unrealdinnerbone.unreallib.ReflectionHelper;
import com.unrealdinnerbone.unreallib.api.LaunchParameter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class LaunchManager
{
    public static void init(String[] args) {
        JCommander.Builder builder = new JCommander.Builder();
        ReflectionHelper.getClassWithAnnotation(LaunchParameter.class).forEach(aClass -> builder.addObject(Objects.requireNonNull(ReflectionHelper.createInstance(aClass))));
        builder.build().parse(args);
    }
}
