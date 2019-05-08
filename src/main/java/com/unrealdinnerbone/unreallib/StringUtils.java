package com.unrealdinnerbone.unreallib;

import com.google.common.base.Preconditions;
import lombok.NonNull;

import java.text.MessageFormat;
import java.util.Arrays;

public class StringUtils
{
    public static final String FORMAT_CODE_START = "{";
    public static final String FORMAT_CODE_END = "}";
    public static final String TAB = "\t";
    public static final String NEW_LINE = "\n";

    public static String replace(String msg, Object... replacements) {
        int count = 0;
        for(Object o : replacements) {
            if(o != null && msg.contains(formatCode(count))) {
                msg = msg.replace(formatCode(count), o.toString());
            }
            count++;
        }
        return msg;
    }

    private static String formatCode(int number) {
        return FORMAT_CODE_START + number + FORMAT_CODE_END;
    }

    public static String capitalizeFirstLetter(String string) {
        checkString(string);
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static String checkString(String string) {
        Preconditions.checkNotNull(string);
        Preconditions.checkArgument(string.length() != 0, "String Length is 0");
        return string;
    }


}
