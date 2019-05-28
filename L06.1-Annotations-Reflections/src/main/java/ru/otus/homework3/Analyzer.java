package ru.otus.homework3;

import ru.otus.homework3.Annotations.Test;

import java.lang.reflect.Method;

class Analyzer {
    void analyz(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        int pass = 0;
        int fail = 0;

        for (Method method : methods) {
            method.setAccessible(true);
            Test test = method.getAnnotation(Test.class);
            Class expected = test.expected();
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(null);
                    pass++;
                } catch (Exception e) {
                    if (Exception.class != expected) {
                        fail++;
                    } else {
                        pass++;
                    }
                }
            }
        }

    }
}
