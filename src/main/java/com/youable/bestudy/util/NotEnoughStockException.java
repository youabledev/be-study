package com.youable.bestudy.util;

public class NotEnoughStockException extends Exception {
    public NotEnoughStockException() {}
    public NotEnoughStockException(String message) {
        super(message);
    }
}
