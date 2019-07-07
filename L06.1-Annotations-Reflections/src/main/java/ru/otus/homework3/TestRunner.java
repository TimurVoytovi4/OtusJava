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
        try (FileWriter logWriter = new FileWriter("L06.1-Annotations-Reflections/src/main/log/"
                + clazz.getSimpleName() + "_log.txt", false)) {

            logWriter.write("* " + clazz.getName() + " * " + ":\n\n");

            Method[] methods = clazz.getMethods();

            Method before = null;
            Method after = null;
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
                        Objects.requireNonNull(before).invoke(before.getDeclaringClass().getConstructor().newInstance());
                        method.invoke(method.getDeclaringClass().getConstructor().newInstance());
                        passed++;
                        callAfter(Objects.requireNonNull(after), logWriter);
                    } catch (InvocationTargetException e) {
                        Throwable exc = e.getTargetException();
                        if (exc.getClass().equals(test.expected())) {
                            passed++;
                            callAfter(Objects.requireNonNull(after), logWriter);
                            continue;
                        }
                        failed++;
                        logWriter.write("- Test " + method.getName() + ": " + exc.getMessage());
                    } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
                        failed++;
                        logWriter.write("- Test " + method.getName() + ": " + e.getMessage());
                    }
                }
            }
            logWriter.write("\n\nTotal: " + (failed + passed) + "\n" +
                    "- Passed: " + passed + "\n" +
                    "- Failed: " + failed + "\n");

            logWriter.flush();
        } catch (IOException | NoSuchMethodException e) {
            System.out.println("Lost access to the file");
        }
    }

    private void callAfter(Method after, FileWriter log) throws IOException {
        try {
            after.invoke(after.getDeclaringClass().getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            failed++;
            log.write("- Test " + after.getName() + ": failed" + "\n");
        }
    }
}
