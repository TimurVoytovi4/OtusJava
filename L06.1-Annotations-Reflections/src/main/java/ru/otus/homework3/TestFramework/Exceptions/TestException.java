package ru.otus.homework3.TestFramework.Exceptions;

public class TestException extends RuntimeException {
    private static String message = "More than one purpose of this method can not be";

    public TestException() {
        super(message);
    }
}
