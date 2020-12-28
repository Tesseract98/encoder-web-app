package com.algorithms.steganography.exceptions;

public class NotEnoughPictureSize extends Exception{

    public NotEnoughPictureSize(String message) {
        super(message);
    }

    public NotEnoughPictureSize(int size) {
        super(String.format("Choose picture bigger than %d", size));
    }

}
