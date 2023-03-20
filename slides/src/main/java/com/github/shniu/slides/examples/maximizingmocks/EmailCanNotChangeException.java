package com.github.shniu.slides.examples.maximizingmocks;

public class EmailCanNotChangeException extends RuntimeException {
    public EmailCanNotChangeException() {
        super("User can't change email.");
    }
}
