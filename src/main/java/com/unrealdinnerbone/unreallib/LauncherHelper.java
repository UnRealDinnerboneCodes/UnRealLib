package com.unrealdinnerbone.unreallib;

import com.beust.jcommander.JCommander;

public class LauncherHelper
{
    public static <T> T get(T t, String... strings) {
        JCommander.newBuilder().addObject(t).build().parse(strings);
        return t;
    }
}
