package com.algorithms.steganography;

import com.algorithms.steganography.exceptions.NotEnoughPictureSize;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class LSBMethodTest {

    private static String path;
    private static LSBMethod lsbMethod;

    @Before
    public void init() {
        path = "src/test/resources/originPic.jpg";
        lsbMethod = LSBMethod.getInstance();
    }

    @Test
    public void loadPicture() throws NotEnoughPictureSize {
        String text = "Hello world!, Привет мир!";

        Path pathSteganFile = lsbMethod.encode(path, text);
        String decodeStr = lsbMethod.decode(pathSteganFile.toString());

        assertEquals(text, decodeStr);
    }

    @Test(expected = NotEnoughPictureSize.class)
    public void overloadPictureSize() throws NotEnoughPictureSize {
        StringBuilder someBigText = new StringBuilder();

        while (someBigText.length() < 768 * 480 + 1) {
            someBigText.append(UUID.randomUUID());
        }

        lsbMethod.encode(path, someBigText.toString());
    }

}