package com.unrealdinnerbone.unreallib;

public class StringUtils {
    public static final String FORMAT_CODE_START = "{";
    public static final String FORMAT_CODE_END = "}";
    public static final String TAB = "\t";
    public static final String NEW_LINE = "\n";

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

    private static String formatCode(int number) {
        return FORMAT_CODE_START + number + FORMAT_CODE_END;
    }

    public static String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
