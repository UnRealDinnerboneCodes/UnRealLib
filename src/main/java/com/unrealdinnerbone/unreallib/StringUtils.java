package com.unrealdinnerbone.unreallib;

import com.google.common.base.Preconditions;
import lombok.NonNull;

import java.text.MessageFormat;
import java.util.Arrays;

public class StringUtils
{
    public static String replace(String msg, Object... replacements) {
        int count = 0;
        for(Object o : replacements) {
            if(msg.contains("{" + count + "}") && o != null) {
                msg = msg.replace("{" + count + "}", o.toString());
            }
            count++;
        }
        return msg;
    }


    public static String capitalizeFirstLetter(String string) {
        checkString(string);
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String checkString(String string) {
        Preconditions.checkNotNull(string);
        Preconditions.checkArgument(string.length() != 0, "String Length is 0");
        return string;
    }


}
