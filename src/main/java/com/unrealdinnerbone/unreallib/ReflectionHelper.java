package com.unrealdinnerbone.unreallib;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class ReflectionHelper {

    public static <T> T createInstance(Class<T> classInstance) {
        try {
            return classInstance.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("There was and error creating a new instance for the class " + classInstance.getName(), e);
            return null;
        }
    }

    public static boolean doesFieldExist(Class<?> clazz, String field) {
        try {
            clazz.getField(field);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    public static boolean doesClassImplementInterface(Class<?> checkClass, Class<?> interfaceClass) {
        return interfaceClass.isAssignableFrom(checkClass);
    }

    public static void setFiled(Field filed, Class<?> clazz, Object o) {
        try {
            filed.set(clazz, o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object getFieldValue(Field field) {
        if (!field.isAccessible()) {
            setFieldAccessible(field);
        }
        try {
            return field.get(field.getType());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldAccessible(Field field) {
        field.setAccessible(true);
    }
}
