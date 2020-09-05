package com.algorithms.compression.haffmancode;

import java.util.*;

public class HuffmanCoding {

    @SuppressWarnings("unused")
    private final String DIGITS = "0123456789";
    private final String ENCRYPTED_NUMBERS = "ƛƞƗƕƍƄƀƾƱƏ";
    private final char MARKER_CHARACTER = 'ă';

    private final List<Integer> code;
    private final Map<Character, List<Integer>> table;

    public HuffmanCoding() {
        code = new LinkedList<>();
        table = new HashMap<>();
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
        if (root.getLetter() != '\0') {
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

        // TODO: think about length
//        int c3=0, c4=0, c5=0;
//        for(char i : table.keySet()) {
//            switch (table.get(i).size()) {
//                case 3:
//                    c3++;
//                    break;
//                case 4:
//                    c4++;
//                    break;
//                case 5:
//                    c5++;
//                    break;
//            }
//        }
//
//        System.out.println(c3 + " " + c4 + " " + c5);
//        System.out.println(c3 * 3 + c4 * 4 + c5 * 5);

        Map<Character, String> tableCharsWithBinaryString = new HashMap<>();
        convertListTableToStringTable(tableCharsWithBinaryString);

        StringBuilder textInBinaryForm = new StringBuilder();
        for (char i : text.toCharArray()) {
            textInBinaryForm.append(tableCharsWithBinaryString.get(i));
        }

        return makeEncryptedText(textInBinaryForm, charactersAndAmounts);
    }

    public String decrypt(String text) {
        Map<Character, Integer> charactersAndAmounts = new HashMap<>();

        int indexOfMarkerChar = readCharactersAndAmountDict(text, text.length(), charactersAndAmounts);

        List<Node> lst = new LinkedList<>();
        fillNodeList(charactersAndAmounts, lst);

        makeSortedTree(lst);

        byte idx = 0;
        if (Character.getNumericValue(text.charAt(indexOfMarkerChar - 1)) != -1) {
            idx = 2;
        }

        StringBuilder vars = new StringBuilder();
        for (int i = 0; i < indexOfMarkerChar - idx; i++) {
            int bt = 1;                           // > 0
            String letter = Integer.toUnsignedString((int) text.charAt(i) - bt, 2);
            int count = 8 - letter.length();
            if (count > 0) {
                for (int j = 0; j < count; j++) {
                    vars.append('0');
                }
            }
            for (char j : letter.toCharArray()) {
                vars.append(j);
            }
        }

        for (int i = indexOfMarkerChar - idx; i < indexOfMarkerChar - 1; i++) {
            String letter = Integer.toUnsignedString((int) text.charAt(i) - 1, 2);
            int numericValue = Character.getNumericValue(text.charAt(i + 1));
            if (letter.length() < numericValue) {
                for (int j = 0; j < numericValue - letter.length(); j++) {
                    vars.append('0');
                }
            }
            for (char j : letter.toCharArray()) {
                vars.append(j);
            }
        }

        return restoreOriginalText(vars, lst);
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
            if (root.getLetter() != '\0') {
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
                    charactersAndAmounts.put(Character.forDigit(ENCRYPTED_NUMBERS.indexOf(textChar), 10), Integer.valueOf(digitStr));
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
        StringBuilder encryptedText = new StringBuilder();

        int codeOfChar = 0;
        int txtLen = textInBinaryForm.length();
        for (int count = 0; count < txtLen - txtLen % 8; count++) {
            int numValueForChar = Character.getNumericValue(textInBinaryForm.toString().charAt(count));
            codeOfChar = codeOfChar | numValueForChar << (7 - (count % 8));
            if ((count + 1) % 8 == 0 || count == txtLen - 1) {
                codeOfChar++;         // > 0
                encryptedText.append((char) codeOfChar);
                codeOfChar = 0;
            }
        }

        int residueBytes = 8 - txtLen % 8;
        for (int count = txtLen - txtLen % 8; count < txtLen; count++) {
            int numValueForChar = Character.getNumericValue(textInBinaryForm.toString().charAt(count));
            codeOfChar = codeOfChar | numValueForChar << (7 - (residueBytes + count % 8));
            if ((count + 1) % 8 == 0 || count == txtLen - 1) {
                codeOfChar++;
                char numberOfEndBits = Character.forDigit(txtLen % 8, 10);
                encryptedText.append((char) codeOfChar);
                encryptedText.append(numberOfEndBits);
                codeOfChar = 0;
            }
        }

        addCharacterDictionaryToEnd(charactersAndAmounts, encryptedText);

        return encryptedText.toString();
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
        while (lst.size() != 1) {
            Collections.sort(lst);
            boolean flg = true;
            while (flg) {
                flg = false;
                for (int i = 0; i < lst.size() - 1; i++) {
                    Node current = lst.get(i);
                    Node nextNode = lst.get(i + 1);
                    if (current.getNumberOfOccurrences() == nextNode.getNumberOfOccurrences()) {
                        if (current.getLetter() < nextNode.getLetter()) {
                            Collections.swap(lst, i, i + 1);
                            flg = true;
                        }
                    }
                }
            }
            Node leftNode = lst.remove(0);
            Node rightNode = lst.remove(0);
            Node parent = new Node(leftNode, rightNode);
            lst.add(parent);
        }
    }

}
