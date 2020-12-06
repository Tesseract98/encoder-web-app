package com.algorithms.compression.haffmancode;

import com.algorithms.steganography.LSBMethod;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class HuffmanCodingTest {

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
    public void encryptDecryptText() {
        HuffmanCoding huffmanCodding = new HuffmanCoding();

        String encrStr = huffmanCodding.encrypt(text);
        String decrStr = huffmanCodding.decrypt(encrStr);

        assertEquals(text, decrStr);
    }

    @Test
    @Ignore("fix some issues")
    public void encryptDecryptFile() throws IOException {
        HuffmanCoding huffmanCodding = new HuffmanCoding();

        Path encrFilePath = huffmanCodding.encrypt(Paths.get(path));
        Path decrFilePath = huffmanCodding.decrypt(encrFilePath);

        System.out.println();
//        assertEquals(text, decrStr);
    }

}