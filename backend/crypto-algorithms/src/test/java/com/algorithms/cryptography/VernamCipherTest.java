package com.algorithms.cryptography;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VernamCipherTest {

    @Test
    public void encryptDecrypt() {
        final String text = "Some Message!1";
        VernamCipher vernamCipher = new VernamCipher();

        String cypherTxt = vernamCipher.encrypt(text);

        assertEquals(text, vernamCipher.decrypt(cypherTxt));
    }

}