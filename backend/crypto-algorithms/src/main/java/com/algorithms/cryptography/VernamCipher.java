package com.algorithms.cryptography;

public class VernamCipher {
    private final String key;
    private final int shift;

    public VernamCipher(String key, int shift) {
        assert shift > 45;
        this.key = key;
        this.shift = shift;
    }

    public VernamCipher() {
        this("F31E64905FCBECF8A0B8595D5E0B73599402C30882C9883CEFDE7B8259A9CFBAB97FE56AB0EB4" +
                "30E6C6AD0786DF7CAC3DF1BFD1E814937D4ABF3767943D40FC6", 155);
    }

    public String encrypt(String message) {
        StringBuilder encryptMsg = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < message.length(); i++) {
            char messageIdx = message.charAt(i);
            if (messageIdx == ' ') {
                encryptMsg.append(' ');
                continue;
            }
            int keyIdx = key.charAt(i % keyLength);
            encryptMsg.append((char) ((messageIdx ^ keyIdx) + shift));
        }
        return encryptMsg.toString();
    }

    public String decrypt(String message) {
        StringBuilder decryptMsg = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < message.length(); i++) {
            char messageIdx = message.charAt(i);
            if (messageIdx == ' ') {
                decryptMsg.append(' ');
                continue;
            }
            int keyIdx = key.charAt(i % keyLength);
            decryptMsg.append((char) ((messageIdx - shift) ^ keyIdx));
        }
        return decryptMsg.toString();
    }

}
