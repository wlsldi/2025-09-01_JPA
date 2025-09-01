package com.kh.app0831.board;

public class BoardException extends RuntimeException {

    public BoardException() {
    }

    public BoardException(String message) {
        super(message);
    }

    public BoardException(String errorCode, String message) {
        super(message);
    }
}