package ru.otus.homework6;

import java.util.*;

public class MyATM implements ATM {
    private Map<Nominal, List<Integer>> storage;
    AtmProcessor pr = new AtmProcessor();

    public MyATM() {
        this.storage = pr.init();
    }

    @Override
    public void set(int cash) {
        pr.storeCash(storage, cash);
    }

    @Override
    public List<Integer> get(int cash) {
        return pr.giveOutCash(storage, cash);
    }

    @Override
    public int size() {
        return storage.values().stream().flatMap(Collection::stream).mapToInt(elem -> elem).sum();
    }
}
