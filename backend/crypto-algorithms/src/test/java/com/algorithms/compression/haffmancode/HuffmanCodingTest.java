package com.algorithms.compression.haffmancode;

import com.algorithms.steganography.LSBMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HuffmanCodingTest {

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
    void encryptDecrypt() {
        HuffmanCoding huffmanCodding = new HuffmanCoding();

        String encrStr = huffmanCodding.encrypt(text);
        String decrStr = huffmanCodding.decrypt(encrStr);

        assertEquals(text, decrStr);
    }

}