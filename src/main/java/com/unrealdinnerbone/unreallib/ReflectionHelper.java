package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.reflection.AIScanner;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class ReflectionHelper {


    public static final Reflections REFLECTIONS = new Reflections("com.unrealdinnerbone", new SubTypesScanner(), new MethodAnnotationsScanner(), new TypeAnnotationsScanner());

    public static <T> Optional<T> createInstance(@NonNull Class<T> classInstance) {
        try {
            return Optional.of(classInstance.getDeclaredConstructor().newInstance());
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException |IllegalAccessException e) {
            log.error("There was and error make and new class instance", e);
            return Optional.empty();
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

    public static <T extends Annotation> Set<Class<?>> getClassWithAnnotation(Class<T> annotationClass) {
        return getClassWithAnnotation(REFLECTIONS, annotationClass);
    }

    public static <T extends Annotation> Set<Class<?>> getClassWithAnnotation(Reflections reflections, Class<T> annotationClass) {
        return reflections.getTypesAnnotatedWith(annotationClass);
    }

    public static <I, A extends Annotation> AIScanner<I, A> iaScan(Class<I> interfaceClass, Class<A> annotationClass) {
        AIScanner<I, A> iaScanner = new AIScanner<>(interfaceClass, annotationClass);
        iaScanner.scan();
        return iaScanner;
    }

}
