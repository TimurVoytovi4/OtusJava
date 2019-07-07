package ru.otus.homework3;


import ru.otus.homework3.Test.CalculatorTest;
import ru.otus.homework3.TestFramework.TestRunner;

public class Demo {
    public static void main(String[] args) {
        new TestRunner(CalculatorTest.class).run();
    }
}
