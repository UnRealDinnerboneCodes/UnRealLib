package com.unrealdinnerbone.unreallib.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TimerEvent
{
    int value();

    TimeUnit timeUnit() default TimeUnit.MINUTES;
}
