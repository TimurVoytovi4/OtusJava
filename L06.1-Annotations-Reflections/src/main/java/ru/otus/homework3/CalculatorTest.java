package ru.otus.homework3;


import ru.otus.homework3.Annotations.Before;
import ru.otus.homework3.Annotations.Test;

public class CalculatorTest {
    private Calculator calculator;
    @Before
    public void init(){
        calculator = new Calculator(5, 9);
    }
    @Test
    public void sum() {
        TestRunner.myAssertEquals(calculator.sum(), 14);
        throw new ArithmeticException();
    }
}