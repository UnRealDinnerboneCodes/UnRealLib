package com.unrealdinnerbone.unreallib.file;

//This Code is Adopted From
//https://github.com/tnm/murmurhash-java/blob/master/src/main/java/ie/ucd/murmur/MurmurHash.java

import java.util.ArrayList;
import java.util.stream.IntStream;

public final class MurmurHash {

    public static long murmurHashHash32(final byte[] data, int seed) {
        int length = data.length;
        final int m = 0x5bd1e995;
        final int r = 24;

        int h = seed ^ length;
        int length4 = length / 4;

        for (int i = 0; i < length4; i++) {
            final int i4 = i * 4;
            int k = (data[i4 + 0] & 0xff) + ((data[i4 + 1] & 0xff) << 8)
                    + ((data[i4 + 2] & 0xff) << 16) + ((data[i4 + 3] & 0xff) << 24);
            k *= m;
            k ^= k >>> r;
            k *= m;
            h *= m;
            h ^= k;
        }

        switch (length % 4) {
            case 3:
                h ^= (data[(length & ~3) + 2] & 0xff) << 16;
            case 2:
                h ^= (data[(length & ~3) + 1] & 0xff) << 8;
            case 1:
                h ^= (data[length & ~3] & 0xff);
                h *= m;
        }
        h ^= h >>> 13;
        h *= m;
        h ^= h >>> 15;


        long returnValue = h;
        //I Don't know why this works but it does ok SO PLEASE DON'T BREAK IT
        if (returnValue < 0) {
            long temp = Integer.MAX_VALUE + returnValue;
            returnValue = Integer.MAX_VALUE + temp + 2;
        }
        return returnValue;
    }

    public static byte[] removeBadValuesFromArray(byte[] bytes) {
        ArrayList<Byte> bytesList = new ArrayList<>();
        for (byte b1 : bytes) {
            if (isGood(b1)) {
                bytesList.add(b1);
            }
        }
        byte[] b = new byte[bytesList.size()];
        IntStream.range(0, b.length).forEach(i -> b[i] = bytesList.get(i));
        return b;
    }

    private static boolean isGood(Byte b) {
        return !isBad(b);
    }

    private static boolean isBad(byte b) {
        return b == 9 || b == 10 || b == 13 || b == 32;
    }
}
