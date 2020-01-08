package ru.otus.homework6;

import java.util.*;


public class MyATM implements ATM {
    private Map<Nominal, Integer> storage;
    UtilProcessor pr = new UtilProcessor();

    public MyATM() {
        storage = new HashMap<>();
    }

    @Override
    public void set(int cash) {
        if (cash != 0) {
            Nominal nominal = pr.findNominal(cash);
            if (nominal != null)
                storage.put(nominal, cash);
            else throw new IllegalArgumentException("Банкомат не может принять данную купюру.");
        }
    }

    @Override
    public List<Integer> get(int cash) {
        pr.recognize(cash);
        return pr.outFilledCash(storage, pr.outList);
    }

    @Override
    public int size() {
        return storage.values().stream().mapToInt(i -> i).sum();
    }
}
