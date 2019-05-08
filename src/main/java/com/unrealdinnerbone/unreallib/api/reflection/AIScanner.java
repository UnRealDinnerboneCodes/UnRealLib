package com.unrealdinnerbone.unrealutil.api.reflection;

import com.unrealdinnerbone.unreallib.Pair;
import com.unrealdinnerbone.unreallib.ReflectionHelper;
import com.unrealdinnerbone.unreallib.api.ILogger;
import com.unrealdinnerbone.unreallib.log.LogHelper;
import lombok.AllArgsConstructor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AIScanner <I, A extends Annotation> implements IScanner<Pair<I, A>> {

    private static final ILogger logger = LogHelper.getLogger(AIScanner.class);

    protected final List<Pair<I, A>> values = new ArrayList<>();

    private final Class<I> interfaceClass;
    private final Class<A> annotationClass;

    private List<Pair<Class<?>, A>> getAnnotatedClasses() {
        return ReflectionHelper.getClassWithAnnotation(annotationClass).stream().<Pair<Class<?>, A>>map(theClass -> new Pair<>(theClass, theClass.getAnnotation(annotationClass))).collect(Collectors.toList());
    }

    @Override
    public List<Pair<I, A>> getValues() {
        return values;
    }

    public List<I> getInterfaces() {
        return values.stream().map(Pair::getA).collect(Collectors.toList());
    }

    public List<A> getAnnotations() {
        return values.stream().map(Pair::getB).collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void scan() {
        for (Pair<Class<?>, A> annotatedClass : getAnnotatedClasses()) {
            if (ReflectionHelper.doesClassImplementInterface(annotatedClass.getA(), interfaceClass)) {
                Object classObject = ReflectionHelper.createInstance(annotatedClass.getA());
                values.add(new Pair<>((I) classObject, annotatedClass.getB()));
            } else {
                logger.error("{0} does not implement {1}", annotatedClass.getA().getName(), annotatedClass.getB().getClass().getName());
            }
        }
    }

}

