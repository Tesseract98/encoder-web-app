package com.algorithms.compression.haffmancode;

import java.util.concurrent.Callable;

class AsyncCharacterConverter implements Callable<String> {

    private final String textInBinaryForm;
    private final int txtLen;
    private final int CHARSET_BYTES_LEN;
    private final int FIRST_CHARSET_BYTE_SHIFT;

    public AsyncCharacterConverter(String textInBinaryForm, int CHARSET_BYTES_LEN) {
        this.textInBinaryForm = textInBinaryForm;
        this.txtLen = textInBinaryForm.length();
        this.CHARSET_BYTES_LEN = CHARSET_BYTES_LEN;
        this.FIRST_CHARSET_BYTE_SHIFT = CHARSET_BYTES_LEN - 1;
    }

    @Override
    public String call() throws Exception {
        StringBuilder encryptedText = new StringBuilder();
//        System.out.println(Thread.currentThread());
        for (int count = 0, codeOfChar = 0; count < txtLen - txtLen % CHARSET_BYTES_LEN; count++) {
            int numValueForChar = Character.getNumericValue(textInBinaryForm.charAt(count));
            codeOfChar = codeOfChar | numValueForChar << (FIRST_CHARSET_BYTE_SHIFT - (count % CHARSET_BYTES_LEN));
            if ((count + 1) % CHARSET_BYTES_LEN == 0 || count == txtLen - 1) {
                codeOfChar++;         // > 0
                encryptedText.append((char) codeOfChar);
                codeOfChar = 0;
            }
        }
        return encryptedText.toString();
    }

}
