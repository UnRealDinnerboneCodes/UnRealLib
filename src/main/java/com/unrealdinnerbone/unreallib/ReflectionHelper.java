package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.api.reflection.AIScanner;
import com.unrealdinnerbone.unreallib.api.reflection.IScanner;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ReflectionHelper
{

    private static final Reflections BASE_REFLECTIONS;

    static {
        BASE_REFLECTIONS = new Reflections("com.unrealdinnerbone");
    }

    public static <T> T createInstance(Class<T> classInstance) {
        try {
            return classInstance.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("There was and error creating a new instance for the class " + classInstance.getName(), e);
            return null;
        }
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

    public static <T> IScanner<T> scan(IScanner<T> iScanner) {
        iScanner.scan();
        return iScanner;
    }

    public static <I, A extends Annotation> List<Pair<I, A>> scan(Class<I> iClass, Class<A> aClass) {
        return scan(new AIScanner<>(iClass, aClass)).getValues();
    }



}
