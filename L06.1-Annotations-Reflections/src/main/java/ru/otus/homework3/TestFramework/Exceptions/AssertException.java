package ru.otus.homework3.TestFramework.Exceptions;

public class AssertException extends RuntimeException {
    private String aCause;

    public AssertException(String aCause) {
        this.aCause = aCause;
    }

    public String getMessage() {
        return aCause;
    }
}
