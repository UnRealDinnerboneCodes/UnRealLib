package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.exception.ExceptionConsumer;
import com.unrealdinnerbone.unreallib.exception.ExceptionSuppler;

public class ExceptionHelper {

    public <T, E extends Exception> void handle(ExceptionSuppler<T, E> suppler, ExceptionConsumer<T, E> exceptionConsumer) throws Exception {
        exceptionConsumer.accept(suppler.get());
    }
}
