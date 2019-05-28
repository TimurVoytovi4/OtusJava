package ru.otus.homework3;


import ru.otus.homework3.Annotations.After;
import ru.otus.homework3.Annotations.Before;
import ru.otus.homework3.Annotations.Test;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator(5, 9);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = Exception.class)
    public void sum() {
        calculator = new Calculator(5, 9);
        System.out.println(calculator.sum());
    }
}