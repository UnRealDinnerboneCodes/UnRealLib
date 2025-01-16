package com.unrealdinnerbone.unreallib;

import java.lang.reflect.Constructor;

public class ReflectionUtils {

    public static <T> ErrorableValue<NoSuchMethodException, Constructor<T>> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return ErrorableValue.value(clazz.getConstructor(parameterTypes));
        } catch (NoSuchMethodException e) {
            return ErrorableValue.error(e);
        }
    }

    public static <T, C> ErrorableValue<ReflectiveOperationException, C> newInstance(Constructor<T> constructor, Object ... initargs) {
        try {
            return ErrorableValue.of((C) constructor.newInstance(initargs));
        } catch (ReflectiveOperationException e) {
            return ErrorableValue.error(e);
        }
    }
}
