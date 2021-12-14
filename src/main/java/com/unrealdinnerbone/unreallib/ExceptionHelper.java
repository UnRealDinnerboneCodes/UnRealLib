package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.exception.ExceptionConsumer;
import com.unrealdinnerbone.unreallib.exception.ExceptionSuppler;

import java.util.Arrays;
import java.util.function.Consumer;

public class ExceptionHelper {

    public <T, E extends Exception> void handle(ExceptionSuppler<T, E> suppler, ExceptionConsumer<T, E> exceptionConsumer, Consumer<E>[] fails) {
        try {
            exceptionConsumer.accept(suppler.get());
        }catch(Exception e){
            Arrays.stream(fails).forEach(fail -> fail.accept((E) e));
        }

    }
}
