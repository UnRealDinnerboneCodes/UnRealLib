package com.unrealdinnerbone.unreallib;

import org.jetbrains.annotations.NotNull;

public interface SimpleColor {

    SimpleColor WHITE = fromRGB(255, 255, 255);
    SimpleColor BLACK = fromRGB(0, 0, 0);
    SimpleColor RED = fromRGB(255, 0, 0);
    SimpleColor GREEN = fromRGB(0, 255, 0);
    SimpleColor BLUE = fromRGB(0, 0, 255);
    SimpleColor YELLOW = fromRGB(255, 255, 0);
    SimpleColor PURPLE = fromRGB(255, 0, 255);
    SimpleColor CYAN = fromRGB(0, 255, 255);
    SimpleColor ORANGE = fromRGB(255, 165, 0);
    SimpleColor PINK = fromRGB(255, 192, 203);
    SimpleColor GRAY = fromRGB(128, 128, 128);
    SimpleColor LIGHT_GRAY = fromRGB(192, 192, 192);
    SimpleColor DARK_GRAY = fromRGB(64, 64, 64);
    SimpleColor BROWN = fromRGB(165, 42, 42);
    
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
