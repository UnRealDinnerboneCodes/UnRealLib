package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.exception.ExceptionSuppler;

import java.util.Arrays;
import java.util.function.Consumer;

public class ExceptionHelper {

    public static <T, E extends Exception> void handle(ExceptionSuppler<T, E> suppler, Consumer<T> exceptionConsumer, Consumer<E> fail) {
        try {
            exceptionConsumer.accept(suppler.get());
        }catch(Exception e){
            fail.accept((E) e);
        }
    }
}
