package com.algorithms.compression.haffmancode;

import com.algorithms.util.ReadProperty;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class HuffmanCoding {

    @SuppressWarnings("unused")
    private final String DIGITS = "0123456789";
    private final String ENCRYPTED_NUMBERS = "ƛƞƗƕƍƄƀƾƱƏ";

    private final String MARKER_CHARACTER = "∅";

    @SuppressWarnings("FieldCanBeLocal")
    private final char INITIAL_NODE_CHARACTER = Node.INITIAL_CHARACTER;

    private final List<Integer> code;
    private final Map<Character, List<Integer>> table;

    private final int CHARSET_BYTES_LEN;
    private final int FIRST_CHARSET_BYTE_SHIFT;

    private final Properties config;

    public HuffmanCoding() {
        config = ReadProperty.getInstance().getConfigProperty();
        code = new LinkedList<>();
        table = new HashMap<>();
        this.CHARSET_BYTES_LEN = Integer.parseInt(config.getProperty("char-length-bytes"));
        this.FIRST_CHARSET_BYTE_SHIFT = CHARSET_BYTES_LEN - 1;
    }

    private void makeBinaryTree(Node root) {
        if (root.getLeft() != null) {
            code.add(0);
            makeBinaryTree(root.getLeft());
        }
        if (root.getRight() != null) {
            code.add(1);
            makeBinaryTree(root.getRight());
        }
        if (root.getLetter() != INITIAL_NODE_CHARACTER) {
            table.put(root.getLetter(), new LinkedList<>(code));
        }
        if (code.size() != 0) {
            code.remove(code.size() - 1);
        }
    }

    public String encrypt(String text) {
        Map<Character, Integer> charactersAndAmounts = new HashMap<>();
        List<Node> nodes = new LinkedList<>();

        fillDictionary(text, charactersAndAmounts);

        fillNodeList(charactersAndAmounts, nodes);

        makeSortedTree(nodes);

        Node root = nodes.get(0);
        makeBinaryTree(root);

        Map<Character, String> tableCharsWithBinaryString = new HashMap<>();
        convertListTableToStringTable(tableCharsWithBinaryString);

        StringBuilder textInBinaryForm = new StringBuilder();
        for (char i : text.toCharArray()) {
            textInBinaryForm.append(tableCharsWithBinaryString.get(i));
        }

        return makeEncryptedText(textInBinaryForm, charactersAndAmounts);
    }

    // TODO: png file decryption
    @Deprecated
    @SuppressWarnings("unused")
    private Path encrypt(Path pathToFile) throws IOException {
        if(!pathToFile.toFile().exists()){
            throw new RuntimeException();
        }

        ImagePlus imagePlus = IJ.openImage(pathToFile.toString());
        ImageProcessor imageProcessor = imagePlus.getProcessor();

        int height = imageProcessor.getHeight();
        int width = imageProcessor.getWidth();

        StringBuilder pixelConvertedChar = new StringBuilder();
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                char str = (char)imageProcessor.getPixel(i, j);
                pixelConvertedChar.append(Integer.toBinaryString(str));
//                pixelConvertedChar.append(str);
            }
        }

        int[][] var = imageProcessor.getIntArray();

        Map<Integer, Integer> charactersAndAmounts = new HashMap<>();

        for(int[] i : var) {
            for (int j : i) {
                if (!charactersAndAmounts.containsKey(j)) {
                    charactersAndAmounts.put(j, 0);
                }
                charactersAndAmounts.put(j, charactersAndAmounts.get(j) + 1);
            }
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(charactersAndAmounts.entrySet());
        list.sort(Map.Entry.comparingByValue((c1, c2) -> -c1.compareTo(c2)));

        Map<Integer, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        System.out.println(result);

        String encryptedFile = encrypt(pixelConvertedChar.toString());

        Path newPath = Paths.get(config.getProperty("save-file-directory") + "asd2.png");

        File file1 = new File(newPath.toString());
        BufferedImage image = new BufferedImage(768, 480, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.createGraphics();
        graphic.setColor(Color.WHITE);
        graphic.fillRect(0, 0, 768, 480);
        for(int i = 0, j = 0; i < 480 && (i * j + j) < encryptedFile.length(); i++) {
            for(j = 0; j < 768 && (i * j + j) < encryptedFile.length();  j++) {
                graphic.setColor(new Color(encryptedFile.charAt(i * j + j)));
                graphic.fillRect(j, i, 1, 1);
            }
            if ((i * j + j) > encryptedFile.length()) {
                System.out.println();
            }
        }

        ImageIO.write(image, "png", file1);

        return newPath;
    }

    public String decrypt(String text) {
        Map<Character, Integer> charactersAndAmounts = new HashMap<>();

        int indexOfMarkerChar = readCharactersAndAmountDict(text, text.length(), charactersAndAmounts);

        List<Node> lst = new LinkedList<>();
        fillNodeList(charactersAndAmounts, lst);

        makeSortedTree(lst);

        byte idx = 0;
        if (Character.getNumericValue(text.charAt(indexOfMarkerChar - 2)) != -1) {
            idx = 3;
        } else if (Character.getNumericValue(text.charAt(indexOfMarkerChar - 1)) != -1) {
            idx = 2;
        }

        int bt = 1; // > 0
        StringBuilder vars = new StringBuilder();
        for (int i = 0; i < indexOfMarkerChar - idx; i++) {
            String letter = Integer.toUnsignedString((int) text.charAt(i) - bt, 2);
            int count = CHARSET_BYTES_LEN - letter.length();
            if (count > 0) {
                vars.append("0".repeat(count));
            }
            vars.append(letter.toCharArray());
        }

        if(idx != 0) {
            String letter = Integer.toUnsignedString((int) text.charAt(indexOfMarkerChar - idx) - bt, 2);
            StringBuilder numResidualBit = new StringBuilder();
            for (int i = indexOfMarkerChar - idx + 1; i < indexOfMarkerChar; i++) {
                numResidualBit.append(text.charAt(i));
            }
            int numericValue = Integer.parseInt(numResidualBit.toString());
            if (letter.length() < numericValue) {
                vars.append("0".repeat(numericValue - letter.length()));
            }
            vars.append(letter.toCharArray());
        }

        return restoreOriginalText(vars, lst);
    }

    // TODO: png file decryption
    @Deprecated
    @SuppressWarnings("unused")
    private Path decrypt(Path pathToFile) throws IOException {
        File file = new File(pathToFile.toString());
        if(!file.exists()){
            throw new RuntimeException();
        }

        byte[] byteFile = IOUtils.toByteArray(new BufferedInputStream(new FileInputStream(file)));

        String symbolicFile = IOUtils
                .toString(new BufferedInputStream(new FileInputStream(file)), StandardCharsets.ISO_8859_1);

        String decryptedFile = decrypt(symbolicFile);

        Path newPath = Paths.get("D:\\new\\new_asd.png");
        File file1 = new File(newPath.toString());
        BufferedOutputStream bfo = new BufferedOutputStream(new FileOutputStream(file1));

//        bfo.write(encryptedFile.getBytes());
        bfo.write(decryptedFile.getBytes("ISO_8859_1"));
//        bfo.write(byteFile);

        bfo.flush();
        bfo.close();

        return newPath;
    }

    private String restoreOriginalText(StringBuilder vars, List<Node> lst) {
        StringBuilder decryptedText = new StringBuilder();
        Node root = lst.get(0);
        for (char j : vars.toString().toCharArray()) {
            if (j == '1') {
                root = root.getRight();
            } else {
                root = root.getLeft();
            }
            if (root.getLetter() != INITIAL_NODE_CHARACTER) {
                decryptedText.append(root.getLetter());
                root = lst.get(0);
            }
        }
        return decryptedText.toString();
    }

    private int readCharactersAndAmountDict(String text, int txtLen, Map<Character, Integer> charactersAndAmounts) {
        String digitStr = "";
        int indexOfMarkerChar = text.indexOf(MARKER_CHARACTER) + 1;
        for (int i = indexOfMarkerChar; i < txtLen; i++) {
            char textChar = text.charAt(i);
            if (textChar < '0' || textChar > '9') {
                if (ENCRYPTED_NUMBERS.contains(String.valueOf(textChar))) {
                    charactersAndAmounts.put(Character.forDigit(ENCRYPTED_NUMBERS.indexOf(textChar), 10),
                            Integer.valueOf(digitStr));
                } else {
                    charactersAndAmounts.put(textChar, Integer.valueOf(digitStr));
                }
                digitStr = "";
            } else {
                digitStr += textChar;
            }
        }
        return indexOfMarkerChar - 1;
    }

    private String makeEncryptedText(StringBuilder textInBinaryForm, Map<Character, Integer> charactersAndAmounts) {
        StringBuilder encryptedText = asyncCharConverter(textInBinaryForm);

        addCharacterDictionaryToEnd(charactersAndAmounts, encryptedText);

        return encryptedText.toString();
    }

    private StringBuilder asyncCharConverter(StringBuilder textInBinaryForm) {
        // use only even numbers (2, 4, 8, 16)
        final int numberOfThreads = 4;
        final int txtLen = textInBinaryForm.length();
        final int numberOfCompleteByte = txtLen - txtLen % CHARSET_BYTES_LEN;

        List<String> subStringTextInBinaryForm = new LinkedList<>();

        // equal balancing not work
//        int step = numberOfCompleteByte / numberOfThreads;
        int step = CHARSET_BYTES_LEN * 2;

        for(int from = 0, till = step, counter = 0; till <= numberOfCompleteByte; from += step, till += step, counter++) {
            if(till + step > numberOfCompleteByte || counter == (numberOfThreads - 1)) {
                till = numberOfCompleteByte;
            }
            subStringTextInBinaryForm.add(textInBinaryForm.substring(from, till));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        // Recursive thread pool
//         ExecutorService executorService = new ForkJoinPool(numberOfThreads);

        StringBuilder encryptedText = new StringBuilder();
        for(String strInBinaryForm : subStringTextInBinaryForm) {
            AsyncCharacterConverter characterConverter = new AsyncCharacterConverter(strInBinaryForm, CHARSET_BYTES_LEN);
            Future<String> future = executorService.submit(characterConverter);
            try {
                encryptedText.append(future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.warn("Something went wrong: {} {}", e.getMessage(), e.getCause());
            }
        }

        fillResidueBytes(textInBinaryForm, encryptedText);

        executorService.shutdown();
        return encryptedText;
    }

    private void fillResidueBytes(StringBuilder textInBinaryForm, StringBuilder encryptedText) {
        int txtLen = textInBinaryForm.length();
        int numberOfCompleteByte = txtLen - txtLen % CHARSET_BYTES_LEN;
        int residueBytes = CHARSET_BYTES_LEN - txtLen % CHARSET_BYTES_LEN;

        for (int count = numberOfCompleteByte, codeOfChar = 0; count < txtLen; count++) {
            int numValueForChar = Character.getNumericValue(textInBinaryForm.toString().charAt(count));
            codeOfChar = codeOfChar | numValueForChar
                    << (FIRST_CHARSET_BYTE_SHIFT - (residueBytes + count % CHARSET_BYTES_LEN));
            if ((count + 1) % CHARSET_BYTES_LEN == 0 || count == txtLen - 1) {
                codeOfChar++;
                String numberOfEndBits = String.valueOf(txtLen % CHARSET_BYTES_LEN);
                encryptedText.append((char) codeOfChar);
                encryptedText.append(numberOfEndBits);
                codeOfChar = 0;
            }
        }

    }

    private void addCharacterDictionaryToEnd(Map<Character, Integer> charactersAndAmounts, StringBuilder encryptedText) {
        encryptedText.append(MARKER_CHARACTER);
        for (char i : charactersAndAmounts.keySet()) {
            encryptedText.append(charactersAndAmounts.get(i));
            if (i >= '0' && i <= '9') {
                encryptedText.append(ENCRYPTED_NUMBERS.charAt(Character.getNumericValue(i)));
            } else {
                encryptedText.append(i);
            }
        }
    }

    private void convertListTableToStringTable(Map<Character, String> tableCharsWithBinaryString) {
        for (char i : table.keySet()) {
            StringBuilder varString = new StringBuilder();
            for (int j : table.get(i)) {
                varString.append(Character.forDigit(j, 10));
            }
            tableCharsWithBinaryString.put(i, varString.toString());
        }
    }

    private void fillNodeList(Map<Character, Integer> charactersAndAmounts, List<Node> nodes) {
        for (char letter : charactersAndAmounts.keySet()) {
            Node el = new Node(letter, charactersAndAmounts.get(letter));
            nodes.add(el);
        }
    }

    private void fillDictionary(String text, Map<Character, Integer> charactersAndAmounts) {
        for (char character : text.toCharArray()) {
            if (!charactersAndAmounts.containsKey(character)) {
                charactersAndAmounts.put(character, 0);
            }
            charactersAndAmounts.put(character, charactersAndAmounts.get(character) + 1);
        }
    }

    private void makeSortedTree(List<Node> lst) {
        Collections.sort(lst);
        while (lst.size() != 1) {
            Node leftNode = lst.remove(0);
            Node rightNode = lst.remove(0);
            Node parent = new Node(leftNode, rightNode);
            lst.add(parent);
        }
    }

}
