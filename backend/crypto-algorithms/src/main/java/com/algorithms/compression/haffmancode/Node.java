package com.algorithms.compression.haffmancode;

class Node implements Comparable<Node> {

    public static final char INITIAL_CHARACTER = 'ৠ';

    private char letter = INITIAL_CHARACTER;
    private final int numberOfOccurrences;

    private Node left;
    private Node right;

    public Node(char letter, int amount) {
        this.letter = letter;
        numberOfOccurrences = amount;
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        numberOfOccurrences = left.getNumberOfOccurrences() + right.getNumberOfOccurrences();
    }

    public Character getLetter() {
        return letter;
    }

    public int getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public int compareTo(Node nextNode) {
        if(this.numberOfOccurrences > nextNode.getNumberOfOccurrences()) {
            return 1;
        } else if (this.numberOfOccurrences < nextNode.getNumberOfOccurrences()){
            return -1;
        } else {
            if (this.letter < nextNode.letter) {
                return 1;
            } else if (this.letter > nextNode.letter) {
                return -1;
            }
            return 0;
        }
    }

}
