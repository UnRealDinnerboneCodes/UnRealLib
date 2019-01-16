package com.unrealdinnerbone.unreallib;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionHelper
{
    private static final Reflections BASE_REFLECTIONS;

    static {
        BASE_REFLECTIONS = new Reflections("com.unrealdinnerbone");
    }


    public static Object createInstance(Class<?> classInstance) {
        try {
            return classInstance.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
        }
        return null;
    }

    public static boolean doesClassImplementInterface(Class<?> checkClass, Class<?> interfaceClass) {
        return interfaceClass.isAssignableFrom(checkClass);
    }
    public static <T extends Annotation> Set<Class<?>> getClassWithAnnotationAndExtands(Class<T> annotationClass, Class<?> interfaceClass) {
        return getClassWithAnnotation(BASE_REFLECTIONS, annotationClass).stream().filter(clazz -> doesClassImplementInterface(clazz, interfaceClass)).collect(Collectors.toSet());
    }

    public static <T extends Annotation> Set<Class<?>> getClassWithAnnotation(Class<T> annotationClass) {
        return getClassWithAnnotation(BASE_REFLECTIONS, annotationClass);
    }

    public static <T extends Annotation> Set<Class<?>> getClassWithAnnotation(Reflections reflections, Class<T> annotationClass) {
        return reflections.getTypesAnnotatedWith(annotationClass);
    }

    public static void a() {
    }


}
