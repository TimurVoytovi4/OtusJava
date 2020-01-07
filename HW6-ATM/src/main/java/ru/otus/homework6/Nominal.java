package ru.otus.homework6;

public enum Nominal {
    FIFTY("50"),
    ONE_HUNDRED("100"),
    TWO_HUNDRED("200"),
    FIVE_HUNDRED("500");

    private final String title;

    Nominal(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
