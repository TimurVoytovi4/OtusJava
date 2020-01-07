package ru.otus.homework6;

import java.util.*;

public class MyATM implements ATM {
    private Map<Nominal, Integer> storage;

    public MyATM() {
        storage = new HashMap<>();
    }

    @Override
    public void set(int cash) {
        for (var var : recognizer(cash))
            storage.put(findNominal(var), cash);
        //TODO save() -> Проверка на корректность сохранения. Что если купюру не распознали
    }

    private List<Integer> recognizer(int cash) {
        /*
        List<Integer> result = new ArrayList<>();
        int a = cash % 50;
        int b = cash % 100;
        int c = cash % 200;
        int d = cash % 500;
        if (d != 0) {
            for (int f = 0; f < d; f++) {
                result.add(500);
            }if (cash)
        }
        */
        return null;
    }

    private Nominal findNominal(int cash) {
        for (Nominal var : Nominal.values()) {
            if (String.valueOf(cash).equals(var.getTitle())) {
                return var;
            }
        }
        return null;
    }

    @Override
    public List<Integer> get(int cash) {
        return recognizer(cash);
    }

    @Override
    public int size() {
        return storage.values().stream().mapToInt(i -> i).sum();
    }
}
