package ru.otus.homework6;

import ru.otus.homework6.Exceptions.NotRecognized;

import java.util.*;

public class MyATM implements ATM {

    private final AtmUtil atmUtil = new AtmUtil();
    private Map<Nominal, List<Nominal>> storage;

    List<Nominal> fiveHundredsCell = new ArrayList<>();
    List<Nominal> twoHundredsCell = new ArrayList<>();
    List<Nominal> oneHundredsCell = new ArrayList<>();
    List<Nominal> fiftiesCell = new ArrayList<>();

    public MyATM() {
        this.storage = new EnumMap<>(Nominal.class);
        Arrays.stream(Nominal.values()).forEach(var -> storage.put(var, Arrays.asList(fiveHundredsCell,
                twoHundredsCell,
                oneHundredsCell,
                fiftiesCell).get(var.ordinal())));
    }

    @Override
    public void set(int cash) {
        Nominal nominal = atmUtil.findNominal(cash);
        if (nominal != null) {
            storage.get(nominal).add(atmUtil.findNominal(cash));
        } else throw new NotRecognized(cash);
    }

    @Override
    public List<Integer> get(int cash) {
        Nominal nominal = atmUtil.findNominal(cash);
        List<Integer> outCash = new ArrayList<>();
        if (nominal == null || storage.get(nominal).isEmpty()) {
            atmUtil.divide(cash).forEach(this::get);
        } else {
            outCash.add(cash);
            storage.get(nominal).remove(nominal);
        }
        return outCash;
    }

    @Override
    public int size() {
        return storage.values().stream().flatMap(Collection::stream).mapToInt(Nominal::getTitle).sum();
    }
}
