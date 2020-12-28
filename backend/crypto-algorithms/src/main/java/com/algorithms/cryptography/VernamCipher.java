package com.algorithms.cryptography;

import com.algorithms.util.ReadProperty;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;

@Slf4j
public class VernamCipher {
    private final String key;
    private final int shift;
    private final Properties config;

    public VernamCipher(String key, int shift) {
        config = ReadProperty.getInstance().getConfigProperty();
        assert shift > 45;
        this.key = key;
        this.shift = shift;
    }

    public VernamCipher() {
        config = ReadProperty.getInstance().getConfigProperty();
        this.key = config.getProperty("vernam-key");
        this.shift = Integer.parseInt(config.getProperty("vernam-shift"));
        assert this.shift > 45;
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

    public int[][] encrypt(int[][] message) {
        int keyLength = key.length();
        for(int i = 0; i < message.length; i++) {
            for (int j = 0; j < message[i].length; j++) {
                int keyIdx = key.charAt(i % keyLength);
                message[i][j] = ((message[i][j] ^ keyIdx) + shift);
            }
        }
        return message;
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

    public int[][] decrypt(int[][] message) {
        int keyLength = key.length();
        for(int i = 0; i < message.length; i++) {
            for (int j = 0; j < message[i].length; j++) {
                int keyIdx = key.charAt(i % keyLength);
                message[i][j] = ((message[i][j] - shift) ^ keyIdx);
            }
        }
        return message;
    }

    public Path encryptPng(Path pathToFile) {
        if(!pathToFile.toFile().exists()) {
            log.warn("empty png file for encryption");
            throw new RuntimeException();
        }
        ImagePlus pngImage = IJ.openImage(pathToFile.toString());
        ImageProcessor pngProcessor = pngImage.getProcessor();

        int[][] encryptedPixels = this.encrypt(pngProcessor.getIntArray());

        ImagePlus newPngImg = pngImage.duplicate();
        ImageProcessor newPngProcessor = newPngImg.getProcessor();

        newPngProcessor.setIntArray(encryptedPixels);

        return saveImage(newPngImg).toAbsolutePath();
    }

    public Path decryptPng(Path pathToFile) {
        if(!pathToFile.toFile().exists()) {
            log.warn("empty png file for decryption");
            throw new RuntimeException();
        }
        ImagePlus pngImage = IJ.openImage(pathToFile.toString());
        ImageProcessor pngProcessor = pngImage.getProcessor();

        int[][] decryptedPixels = this.decrypt(pngProcessor.getIntArray());

        ImagePlus newPngImg = pngImage.duplicate();
        ImageProcessor newPngProcessor = newPngImg.getProcessor();

        newPngProcessor.setIntArray(decryptedPixels);

        return saveImage(newPngImg).toAbsolutePath();
    }

    private Path saveImage(ImagePlus imagePlus) {
        String path = String.format("%s%s", config.getProperty("save-file-directory"), UUID.randomUUID());
        String type = "png";
        Path endPath = Paths.get(String.format("%s.%s", path, type));
        IJ.saveAs(imagePlus, type, path);
        return endPath;
    }

}
