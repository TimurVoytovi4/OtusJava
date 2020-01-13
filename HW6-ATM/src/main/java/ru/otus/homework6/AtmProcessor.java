package ru.otus.homework6;

import ru.otus.homework6.Exceptions.NotRecognized;

import java.util.*;

class AtmProcessor {

    private final AtmUtil atmUtil = new AtmUtil();
    private Nominal nominal;
    private Map<Nominal, List<Nominal>> storage;

    List<Integer> outCash = new ArrayList<>();

    void init(List<List<Nominal>> asList) {
        storage = new EnumMap<>(Nominal.class);
        Arrays.stream(Nominal.values()).forEach(var -> storage.put(var, asList.get(var.ordinal())));
    }

    void storeCash(int cash) {
        if (check(cash)) {
            storage.get(nominal).add(atmUtil.findNominal(cash));
        } else throw new NotRecognized(cash);
    }

    private boolean check(int cash) {
        nominal = atmUtil.findNominal(cash);
        return nominal != null;
    }

    List<Integer> giveCash(int cash) {
        if (!check(cash) || storage.get(atmUtil.findNominal(cash)).isEmpty()) {
            atmUtil.divide(cash).forEach(this::giveCash);
        } else {
            outCash.add(cash);
            storage.get(nominal).remove(nominal);
        }
        return outCash;
    }

    int size() {
        return storage.values().stream().flatMap(Collection::stream).mapToInt(Nominal::getTitle).sum();
    }
}
