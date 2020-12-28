package com.algorithms.compression.haffmancode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HuffmanCodingTest {

    @Test
    public void encryptDecryptText1() {
        final String text = "Hello world!1 Привет мир!";

        HuffmanCoding huffmanCodding = new HuffmanCoding();

        String encrStr = huffmanCodding.encrypt(text);
        String decrStr = huffmanCodding.decrypt(encrStr);

        assertEquals(text, decrStr);
    }

    @Test
    public void encryptDecryptText2() {
        final String text = "Привет 12345 Hello 67890";

        HuffmanCoding huffmanCodding = new HuffmanCoding();

        String encrStr = huffmanCodding.encrypt(text);
        String decrStr = huffmanCodding.decrypt(encrStr);

        assertEquals(text, decrStr);
    }

}