package com.algorithms.steganography;

import com.algorithms.steganography.exceptions.NotEnoughPictureSize;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class LSBMethodTest {

    private static String text;
    private static String path;
    private static LSBMethod lsbMethod;

    @Before
    public void init() {
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