package ru.otus.homework3;

import ru.otus.homework3.Annotations.After;
import ru.otus.homework3.Annotations.Before;
import ru.otus.homework3.Annotations.MockClass;
import ru.otus.homework3.Annotations.Test;

import java.lang.reflect.Method;

import static java.lang.System.*;

class TestRunner {
    private static boolean statusTest = false;
    private static boolean notAssert = true;

    private int passed = 0;
    private int failed = 0;
    private int error = 0;
    private boolean ok = true;

    private double time = 0;

    void run(Class<?> clazz){
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                out.println("BEFORE");
            } else if (method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                try {
                    time = nanoTime();
                    method.invoke(clazz.getDeclaredConstructor().newInstance());
                    double newTime = nanoTime() - time;
                    time = newTime / 1000000;
                    if (!annotation.expected().equals(MockClass.class)) {
                        out.println("\r\nFor the method (" + method.getName() + ") does not meet the exception -> " + annotation.expected());
                    } else {
                        out.println("\r\nMETHOD " + method.getName() + "()");
                        out.println("\r\nRun-time: " + time + " ms");
                        if (statusTest) {
                            passed++;
                            if (!notAssert) {
                                out.print("\r\nTest: PASSED\r\n");
                                notAssert = true;
                            }
                            statusTest = false;
                        } else {
                            failed++;
                            if (!notAssert) {
                                out.println("\r\nTest: FAILED\r\n");
                                notAssert = true;
                            }
                        }
                    }
                } catch (Throwable e) {
                    if (annotation.expected().equals(e.getCause().getClass())) {
                        out.println("\r\nMETHOD " + method.getName() + "()");
                        out.println("\r\nRun-time: " + time + " ms");
                        if (statusTest) {
                            passed++;
                            out.print("\r\nTest: PASSED\r\n");
                        } else {
                            failed++;
                            out.println("\r\nTest: FAILED\r\n");
                            statusTest = false;
                        }

                    } else {
                        out.print("\r\nIn test " + method.getName() + "()" + " Error: " + e.getCause());
//                        err.printf("\r\nIn test " + method.getName() + "()" + " Error: " + e.getCause());
                        error++;
                        ok = false;
                    }
                }
            } else if (method.isAnnotationPresent(After.class)) {
                out.println("After");
            }
        }
        out.print("\r\n------------------------------");
        out.print("\r\nPassed: " + passed + " Failed " + failed + " Error " + error);
        out.println("\r\nThe test is completed: " + (ok ? "OK" : "Failed"));
    }


    static boolean myAssertEquals(Object obj1, Object obj2){
        boolean result = obj1.equals(obj2);
        if(result){
            statusTest = true;
        }
        notAssert = false;
        return result;
    }

    static boolean myAssertEquals(int a, int b){
        boolean result = a==b;
        if(result){
            statusTest = true;
        }
        notAssert = false;
        return result;
    }

    public static boolean myAssertEquals(boolean result, boolean to){
        boolean resultTo = result==to;
        if(result){
            statusTest = true;
        }
        notAssert = false;
        return resultTo;
    }

    public static boolean myAssertEquals(String st1, String st2){
        boolean result = st1.equals(st2);
        if(result){
            statusTest = true;
        }
        notAssert = false;
        return result;
    }
}
