package com.algorithms.cryptography;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VernamCipherTest {

    @Test
    void encryptDecrypt() {
        final String text = "Some Message!1";
        VernamCipher vernamCipher = new VernamCipher();

        String cypherTxt = vernamCipher.encrypt(text);

        assertEquals(text, vernamCipher.decrypt(cypherTxt));
    }

}