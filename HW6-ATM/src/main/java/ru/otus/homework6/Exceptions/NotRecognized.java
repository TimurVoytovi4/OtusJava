package ru.otus.homework6.Exceptions;

public class NotRecognized extends AtmException {
    public NotRecognized(int cash) {
        super("Не распознана данная купюра: " + cash);
    }
}
