package ru.otus.homework6;

import ru.otus.homework6.Exceptions.NotRecognized;

import java.util.*;

class AtmProcessor {

    private Nominal nominal;
    private AtmUtil util = new AtmUtil();
    private Map<Nominal, List<Nominal>> storage;

    List<Integer> outCash = new ArrayList<>();

    void init(List<List<Nominal>> asList) {
        storage = new EnumMap<>(Nominal.class);
        Arrays.stream(Nominal.values()).forEach(var -> storage.put(var, asList.get(var.ordinal())));
    }

    void storeCash(int cash) {
        if (check(cash)) {
            storage.get(nominal).add(findNominal(cash));
        } else throw new NotRecognized(cash);
    }

    private boolean check(int cash) {
        nominal = findNominal(cash);
        return nominal != null;
    }

    private Nominal findNominal(int cash) {
        return Arrays.stream(Nominal.values()).filter(var -> cash == var.getTitle()).findFirst().orElse(null);
    }

    List<Integer> giveCash(int cash) {
        if (!check(cash)) {
            util.recognize(cash).forEach(this::giveCash);
        } else if (storage.get(findNominal(cash)).isEmpty()) {
            util.divide(cash).forEach(this::giveCash);
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
