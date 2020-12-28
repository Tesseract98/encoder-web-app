package com.algorithms.cryptography;

import ij.IJ;
import ij.process.ImageProcessor;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class VernamCipherTest {

    private static String path;

    @Before
    public void init() {
        path = "src/test/resources/originPic.jpg";
    }

    @Test
    public void encryptDecrypt() {
        final String text = "Hello world!, Привет мир!";
        VernamCipher vernamCipher = new VernamCipher();

        String cypherTxt = vernamCipher.encrypt(text);

        assertEquals(text, vernamCipher.decrypt(cypherTxt));
    }

    @Test
    public void encryptDecryptCustom() {
        final String text = "Hello world!, Привет мир!";
        VernamCipher vernamCipher = new VernamCipher("some_key", 55);

        String cypherTxt = vernamCipher.encrypt(text);

        assertEquals(text, vernamCipher.decrypt(cypherTxt));
    }

    @Test
    public void encryptDecryptFile() {
        VernamCipher vernamCipher = new VernamCipher();

        Path encrFilePath = vernamCipher.encryptPng(Paths.get(path));
        Path decrFilePath = vernamCipher.decryptPng(encrFilePath);

        ImageProcessor expected = IJ.openImage(path).getProcessor();
        ImageProcessor actual = IJ.openImage(decrFilePath.toString()).getProcessor();

        assertArrayEquals(expected.getIntArray(), actual.getIntArray());
    }

    @Test(expected = RuntimeException.class)
    public void emptyEncryptFile() {
        VernamCipher vernamCipher = new VernamCipher();

        vernamCipher.encryptPng(Paths.get("fail_path"));
    }

    @Test(expected = RuntimeException.class)
    public void emptyDecryptFile() {
        VernamCipher vernamCipher = new VernamCipher();

        vernamCipher.decryptPng(Paths.get("fail_path"));
    }

}