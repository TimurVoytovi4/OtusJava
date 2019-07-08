package ru.otus.homework3.TestFramework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.homework3.Annotations.After;
import ru.otus.homework3.Annotations.Before;
import ru.otus.homework3.Annotations.Test;
import ru.otus.homework3.TestFramework.Exceptions.TestException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class TestRunner {
    private Class clazz;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public TestRunner(Class clazz) {
        this.clazz = clazz;
    }

    private int passed = 0;
    private int failed = 0;
    private Method before = null;
    private Method after = null;

    public void run() {
        try {
            Method[] methods = clazz.getMethods();
            methodNominator(methods);
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    Test test = method.getAnnotation(Test.class);
                    try {
                        callMethod(Objects.requireNonNull(before));
                        method.invoke(method.getDeclaringClass().getConstructor().newInstance());
                        passed++;
                    } catch (InvocationTargetException e) {
                        Throwable exc = e.getTargetException();
                        if (exc.getClass().equals(test.expected())) {
                            passed++;
                            callMethod(Objects.requireNonNull(after));
                            continue;
                        }
                        failed++;
                        logger.error("- Test " + method.getName() + ": " + exc.getMessage());
                    } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
                        failed++;
                        logger.error("- Test " + method.getName() + ": " + e.getMessage());
                    } finally {
                        callMethod(Objects.requireNonNull(after));
                    }
                }
            }
            logger.info("\n\nTotal: " + (failed + passed) + "\n" +
                    "- Passed: " + passed + "\n" +
                    "- Failed: " + failed + "\n");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void methodNominator(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class))
                if (before != null) throw new TestException();
                else before = method;
            if (method.isAnnotationPresent(After.class))
                if (after != null) throw new TestException();
                else after = method;
        }
    }

    private void callMethod(Method method) {
        try {
            method.invoke(method.getDeclaringClass().getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            failed++;
            logger.error("- Test " + method.getName() + ": failed" + "\n");
        }
    }
}
