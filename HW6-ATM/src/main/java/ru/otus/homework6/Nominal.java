package ru.otus.homework6;

public enum Nominal {
    FIVE_HUNDRED(500),
    TWO_HUNDRED(200),
    ONE_HUNDRED(100),
    FIFTY(50);

    private final int title;

    Nominal(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
    }
}
