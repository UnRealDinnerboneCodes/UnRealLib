package com.unrealdinnerbone.unreallib.reflection;

import java.util.List;

public interface IScanner<R> {
    void scan();

    List<R> getValues();
}
