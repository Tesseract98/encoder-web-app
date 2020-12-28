package com.algorithms.steganography;

import com.algorithms.steganography.exceptions.NotEnoughPictureSize;
import com.algorithms.util.ReadProperty;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;

public class LSBMethod {

    private final int UNICODE_SEQUENCE_BIT = 16;

    private final int AMOUNT_OF_ZEROS = UNICODE_SEQUENCE_BIT + 5;

    private static final LSBMethod lsbMethod = new LSBMethod();

    private final Properties configProperty;
    private int height;
    private int width;

    private LSBMethod() {
        configProperty = ReadProperty.getInstance().getConfigProperty();
    }

    public static LSBMethod getInstance() {
        return lsbMethod;
    }

    // TODO: find least significant bit in RGB array
    // int pixel[] = imp.getPixel(i, j);
    // ip.putPixel(i, j, pixel);

    public synchronized Path encode(String path, String text) throws NotEnoughPictureSize {
        ImagePlus imagePlus = loadPicture(path);
        ImageProcessor imageProcessor = getImageProcessor(imagePlus);

        String binaryText = getBinaryText(text);

        if (binaryText.length() >= height * width) {
            throw new NotEnoughPictureSize(binaryText.length());
        }

        int i = 0, j = 0;
        for (char c: binaryText.toCharArray()) {
            if (j + 1 > height) {
                i++;
                j = 0;
            }
            int pixel = imageProcessor.getPixel(i, j);
            pixel = (pixel - pixel % 2) + Character.getNumericValue(c);
            imageProcessor.set(i, j, pixel); // work faster than putPixel
            j++;
        }
        imageProcessor.rotate(180);

        return saveImage(imagePlus);
    }

    public synchronized String decode(String path) {
        ImagePlus imagePlus = loadPicture(path);
        ImageProcessor imageProcessor = getImageProcessor(imagePlus);

        StringBuilder result = new StringBuilder();
        StringBuilder varStr16Chars = new StringBuilder();
        int stopCounter = 0;
        for(int i = 0; i < width && stopCounter < AMOUNT_OF_ZEROS; i++) {
            for(int j = 0; j < height && stopCounter < AMOUNT_OF_ZEROS; j++) {
                byte b = (byte) (Math.abs(imageProcessor.getPixel(i, j)) % 2);
                varStr16Chars.append(Character.forDigit(b, 10));
                if(varStr16Chars.length() == UNICODE_SEQUENCE_BIT) {
                    String string16Chars = varStr16Chars.toString();
                    if(string16Chars.contains("1")) {
                        result.append((char) Integer.parseInt(string16Chars, 2));
                    }
                    varStr16Chars = new StringBuilder();
                }
                stopCounter = (b == 0 ? stopCounter + 1 : 0);
            }
        }
        return result.toString();
    }

    private ImagePlus loadPicture(String path) {
        return IJ.openImage(path);
    }

    private ImageProcessor getImageProcessor(ImagePlus imp) {
        ImageProcessor ip = imp.getProcessor();

        ip.rotate(180);

        this.height = imp.getHeight();
        this.width = imp.getWidth();

        return ip;
    }

    private String getBinaryText(String text) {
        StringBuilder result = new StringBuilder();
        for(char c: text.toCharArray()) {
            String binaryStr = Integer.toBinaryString(c);
            get16LenUnicodeSequence(result, binaryStr);
            result.append(binaryStr);
        }
        putEndOfTextMarker(result);
        return result.toString();
    }

    private void putEndOfTextMarker(StringBuilder result) {
        result.append("0".repeat(AMOUNT_OF_ZEROS));
    }

    private void get16LenUnicodeSequence(StringBuilder result, String binaryStr) {
        result.append("0".repeat(Math.max(0, UNICODE_SEQUENCE_BIT - binaryStr.length())));
    }

    private Path saveImage(ImagePlus imagePlus) {
        String path = String.format("%s%s", configProperty.getProperty("save-file-directory"), UUID.randomUUID());
        String type = configProperty.getProperty("file-type");
        Path endPath = Paths.get(String.format("%s.%s", path, type));
        IJ.saveAs(imagePlus, type, path);
        return endPath;
    }

}
