package ru.otus.homework3;

import ru.otus.homework3.Annotations.After;
import ru.otus.homework3.Annotations.Before;
import ru.otus.homework3.Annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

class TestRunner {
    private Class clazz;

    TestRunner(Class clazz) {
        this.clazz = clazz;
    }

    private int passed = 0;
    private int failed = 0;

    void run() {
        try {
            FileWriter testWriter = new FileWriter(clazz.getName() + "_log.txt", false);

            testWriter.write("* " + clazz.getName() + " * " +  ":\n\n");

            Method[] methods = clazz.getMethods();

            Method before = TestRunner.class.getDeclaredMethod("nothing");
            Method after = TestRunner.class.getDeclaredMethod("nothing");
            for (Method method : methods) {
                if (method.isAnnotationPresent(Before.class)) {
                    before = method;
                }
                if (method.isAnnotationPresent(After.class)) {
                    after = method;
                }
            }
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    Test test = method.getAnnotation(Test.class);
                    try {
                        before.invoke(before.getDeclaringClass().getConstructor().newInstance());
                        method.invoke(method.getDeclaringClass().getConstructor().newInstance());
                        passed++;
                        callAfter(Objects.requireNonNull(after));
                    } catch (InvocationTargetException e) {
                        Throwable exc = e.getTargetException();
                        if (exc.getClass().equals(test.expected())) {
                            passed++;
                            callAfter(Objects.requireNonNull(after));
                            continue;
                        }
                        failed++;
                        testWriter.write("- Test " + method.getName() + ": " + exc.getMessage());
                    } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
                        failed++;
                        testWriter.write("- Test " + method.getName() + ": " + e.getMessage());
                    }
                }
            }
            testWriter.write("\n\nTotal: " + (failed + passed) + "\n" +
                    "- Passed: " + passed + "\n" +
                    "- Failed: " + failed + "\n");

            testWriter.flush();
        } catch (IOException | NoSuchMethodException e) {
            System.out.println("Lost access to the file");
        }
    }
    private void callAfter(Method after)
    {
        try
        {
            after.invoke(after.getDeclaringClass().getConstructor().newInstance());
        }
        catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e)
        {
            System.out.println("Invalid after method");
        }
    }
    private void nothing(){}
}
