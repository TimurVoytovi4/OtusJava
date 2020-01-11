package ru.otus.homework6;

import java.util.*;

public class MyATM implements ATM {
    AtmProcessor pr = new AtmProcessor();

    public MyATM() {
        List<Nominal> fiveHundredsCell = new ArrayList<>();
        List<Nominal> twoHundredsCell = new ArrayList<>();
        List<Nominal> oneHundredsCell = new ArrayList<>();
        List<Nominal> fiftiesCell = new ArrayList<>();
        pr.init(Arrays.asList(fiveHundredsCell,
                twoHundredsCell,
                oneHundredsCell,
                fiftiesCell));
    }

    @Override
    public void set(int cash) {
        pr.storeCash(cash);
    }

    @Override
    public List<Integer> get(int cash) {
        List<Integer> returnCash = new ArrayList<>(pr.giveCash(cash));
        pr.outCash.clear();
        return returnCash;
    }

    @Override
    public int size() {
        return pr.size();
    }
}
