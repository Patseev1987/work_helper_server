package ru.diploma.inflate_server.exceptions;

public class MyCustomException extends RuntimeException {
    public MyCustomException(String message) {
        super(message);
    }
}
