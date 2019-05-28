package ru.otus.homework3;

class Calculator {
    private int a;
    private int b;

    Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int sum() {
        return a + b;
    }
}
