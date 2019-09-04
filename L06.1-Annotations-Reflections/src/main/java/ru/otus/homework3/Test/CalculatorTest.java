package ru.otus.homework3.Test;


import ru.otus.homework3.Annotations.After;
import ru.otus.homework3.Annotations.Before;
import ru.otus.homework3.Annotations.Test;
import ru.otus.homework3.TestFramework.Assert;
import ru.otus.homework3.Calculator;

public class CalculatorTest {
    private static Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator(5, 9);
    }

    @Test
    public void test1() {
        Assert.assertEquals(calculator.sum(), 14);
    }

    @Test
    public void test2() {
        Assert.assertEquals(calculator.sum(), 15);
    }

    @After
    public void afterTest() {
        Assert.assertNotNull(calculator);
        calculator = null;
    }
}