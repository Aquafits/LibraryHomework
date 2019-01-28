package com.aquafits.library.exceptions;

public class BorrowException extends RuntimeException {

    public BorrowException() {
    }

    public BorrowException(String message) {
        super(message);
    }
}
