package com.unrealdinnerbone.unreallib;

import org.jetbrains.annotations.NotNull;

public interface SimpleColor {


    @NotNull
    static SimpleColor fromRGB(int red, int green, int blue) {
        return new RGB(red, green, blue);
    }

    @NotNull
    static SimpleColor fromHex(String hex) {
        if(hex == null) {
            throw new IllegalArgumentException("Hex cannot be null");
        }
        if(!hex.startsWith("#")) {
            throw new IllegalArgumentException("Hex must start with #");
        }
        hex = hex.substring(1);
        try {
            return fromRGB(
                    Integer.valueOf(hex.substring(0, 2), 16),
                    Integer.valueOf(hex.substring(2, 4), 16),
                    Integer.valueOf(hex.substring(4, 6), 16));
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex string", e);
        }
    }

    int red();

    int green();

    int blue();

    default String hex() {
        return String.format("#%02x%02x%02x", red(), green(), blue());
    }

    record RGB(int red, int green, int blue) implements SimpleColor { }

    default int asRGB() {
        return red() << 16 | green() << 8 | blue();
    }
}
