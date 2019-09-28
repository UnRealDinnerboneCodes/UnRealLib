package com.unrealdinnerbone.unreallib;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Predicate;

@Slf4j
public class DebugUtil
{
    public static void printFieldDebug(Class clazz, Predicate<? super Field> predicate) {
        Arrays.stream(clazz.getDeclaredFields()).filter(predicate).forEach(declaredField -> log.debug("Field {} == {}", declaredField.getName(), ReflectionHelper.getFieldValue(declaredField)));
    }

    public static void printFieldDebug(Class clazz) {
        printFieldDebug(clazz, field -> true);
    }
}
