package com.unrealdinnerbone.unreallib;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {
    public static final String FORMAT_CODE_START = "{";
    public static final String FORMAT_CODE_END = "}";
    public static final String TAB = "\t";
    public static final String NEW_LINE = "\n";
    public static final String EMPTY = "";


    public static String replace(String msg, Object... replacements) {
        int count = 0;
        for (Object o : replacements) {
            String formatCode = formatCode(count);
            if (o != null && msg.contains(formatCode)) {
                msg = msg.replace(formatCode, o.toString());
            }
            count++;
        }
        return msg;
    }

    public static String trim(String string, int size) {
        return string.length() > size ? string.substring(0, size - 1) : string;
    }
    public static String removePunstion(String s) {
        return s.replaceAll("[^a-zA-Z ]", "");
    }

    private static String formatCode(int number) {
        return FORMAT_CODE_START + number + FORMAT_CODE_END;
    }

    public static String capitalizeFirstLetter(String string) {
        return string.isEmpty() ? string : string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
    public static String toCamelCase(String s){
        return Arrays.stream(s.split(" "))
                .map(StringUtils::capitalizeFirstLetter)
                .collect(Collectors.joining());
    }

    public static boolean containsUpperCase(String string) {
        return string.chars().anyMatch(Character::isUpperCase);
    }
}
