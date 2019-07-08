package ru.otus.homework3.TestFramework;

import ru.otus.homework3.TestFramework.Exceptions.AssertException;

public class Assert {

    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual))
            throw new AssertException("Objects are not equal. ");
    }

    public static void assertTrue(boolean condition) {
        if (!condition)
            throw new AssertException("Condition is not true. ");
    }

    public static void assertTrue(String message, String condition) {
        if (!message.equals(condition))
            throw new AssertException("Conditions are not equal. ");
    }

    public static void assertNull(Object object) {
        if (object == null)
            throw new AssertException("Object is null. ");
    }

    public static void assertNotNull(Object object) {
        if (object != null)
            throw new AssertException("Object is not null. ");
    }

}

