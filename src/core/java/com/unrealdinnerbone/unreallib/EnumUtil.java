package com.unrealdinnerbone.unreallib;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EnumUtil
{
    public static <T extends Enum<?>> T getRandomEnum(Class<T> clazz) {
        return clazz.getEnumConstants()[MathHelper.getRandom().nextInt(clazz.getEnumConstants().length)];
    }

    public static <T extends Enum<?>> List<T> getEnumsFromClass(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    public static <T extends Enum<T>> Optional<T> getEnumValueFromName(Class<T> enumClass, String name) {
        return EnumUtil.getEnumsFromClass(enumClass).stream().filter(enumValue -> enumValue.name().equalsIgnoreCase(name)).findFirst();
    }
}
