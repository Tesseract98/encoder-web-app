package com.algorithms.steganography;

import com.algorithms.steganography.exceptions.NotEnoughPictureSize;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LSBMethodTest {

    private static String text;
    private static String path;
    private static LSBMethod lsbMethod;

    @BeforeAll
    public static void init() {
        text = "Hello world!, Привет мир!";
        path = "src/test/resources/originPic.jpg";
        lsbMethod = LSBMethod.getInstance();
    }

    @Test
    public void loadPicture() throws NotEnoughPictureSize {
        Path pathSteganFile = lsbMethod.encode(path, text);

        String decodeStr = lsbMethod.decode(pathSteganFile.toString());
        assertEquals(text, decodeStr);

        System.out.println(decodeStr);
    }

}