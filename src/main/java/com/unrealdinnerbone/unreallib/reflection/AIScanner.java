package com.unrealdinnerbone.unreallib.reflection;

import com.unrealdinnerbone.unreallib.Pair;
import com.unrealdinnerbone.unreallib.ReflectionHelper;
import lombok.AllArgsConstructor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AIScanner<I, A extends Annotation> implements IScanner<Pair<I, A>> {

    protected final List<Pair<I, A>> values = new ArrayList<>();

    private final Class<I> interfaceClass;
    private final Class<A> annotationClass;

    private List<Pair<? extends Class<?>, A>> getAnnotatedClasses() {
        return ReflectionHelper.getClassWithAnnotation(annotationClass).stream().map(aClass -> new Pair<>(aClass, aClass.getAnnotation(annotationClass))).collect(Collectors.toList());
    }

    @Override
    public List<Pair<I, A>> getValues() {
        return values;
    }

    public List<I> getInterfaces() {
        return values.stream().map(Pair::getKey).collect(Collectors.toList());
    }

    public List<A> getAnnotations() {
        return values.stream().map(Pair::getValue).collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void scan() {
        getAnnotatedClasses().stream()
                .filter(aPair -> ReflectionHelper.doesClassImplementInterface(aPair.getKey(), interfaceClass))
                .map(aPair -> Pair.of(ReflectionHelper.createInstance(aPair.getKey()).orElse(null), aPair))
                .filter(pairPair -> pairPair.getValue().getKey() != null)
                .forEach(o -> values.add(Pair.of((I) o.getKey(), o.getValue().getValue())));
    }

}
