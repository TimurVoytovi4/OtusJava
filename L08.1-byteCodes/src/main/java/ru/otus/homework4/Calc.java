package ru.otus.homework4;

public class Calc {
    @Log
    public void sum(int a, int b) {
        System.out.println(a + b);
    }
    @Override
    public String toString() {
        return "Calc{}";
    }
}
