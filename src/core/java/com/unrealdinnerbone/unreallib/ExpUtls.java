package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.exception.ExceptionSuppler;

public class ExpUtls
{
    public static <T, E extends Exception> T throwUp(ExceptionSuppler<T, E> suppler) throws E {
        return suppler.get();
    }
}
