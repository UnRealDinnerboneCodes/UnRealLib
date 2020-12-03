package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.web.HttpUtils;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class OptionalUtils {

    public static <T, E extends Exception, R> R returnIfPresentOrElse(Optional<T> optional, ExceptionFunction<E, R, T> function, ExceptionSuppler<R, E> exceptionSuppler) throws E {
        return optional.isPresent() ? function.get(optional.get()) : exceptionSuppler.get();
    }
}
