package com.algoriant.cvs.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

public class StudentImageUtil {

    public static byte[] compressImage(byte[] studentImageData) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(studentImageData);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(studentImageData.length);
        byte[] temp = new byte[4 * 1024];

        while (!deflater.finished()) {
            int size = deflater.deflate(temp);
            outputStream.write(temp, 0, size);
        }

        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
}
