package ru.otus.homework6;

import ru.otus.homework6.Exceptions.AtmException;
import ru.otus.homework6.Exceptions.NotRecognized;

import java.util.*;

class AtmProcessor {
    Nominal nominal;
    List<Integer> outCash = new ArrayList<>();

    Map<Nominal, List<Integer>> init() {
        return new EnumMap<>(Nominal.class);
    }

    public void storeCash(Map<Nominal, List<Integer>> storage, int cash) {
        try {
            if (check(cash)) {
                storage.computeIfAbsent(nominal, k -> new ArrayList<>());
                storage.get(nominal).add(cash);
            }
        } catch (NotRecognized e) {
            System.out.println(e.getMessage());
        }
    }

    boolean check(int cash) {
        switch (cash) {
            case 500:
            case 200:
            case 100:
            case 50:
                nominal = findNominal(cash);
                return true;
        }
        throw new NotRecognized(cash);
    }

    List<Integer> giveOutCash(Map<Nominal, List<Integer>> storage, int cash) {
        if (cash != 0) {
            if (!storage.isEmpty()) {
                recognize(cash);
                if (checkCashExist(storage, outCash))
                    return extractCash(storage, outCash);
            }
            throw new AtmException("Недостаточно средств в банкомате");
        }
        throw new NotRecognized(cash);
    }

    void recognize(int cash) {
        if (cash >= 500)
            fillCash(500, cash);
        else if (cash >= 200)
            fillCash(200, cash);
        else if (cash >= 100)
            fillCash(100, cash);
        else if (cash >= 50)
            fillCash(50, cash);
        else throw new NotRecognized(cash);
    }

    private void fillCash(int nominal, int cash) {
        int k = cash % nominal;
        for (int j = cash / nominal; j > 0; j--) outCash.add(nominal);
        if (k != 0) recognize(k);
    }

    private Nominal findNominal(int cash) {
        return Arrays.stream(Nominal.values()).filter(var -> cash == Integer.parseInt(var.getTitle())).findFirst().orElse(null);
    }

    private List<Integer> extractCash(Map<Nominal, List<Integer>> storage, List<Integer> outCash) {
        List<Integer> tmp = new ArrayList<>(outCash);
        outCash.clear();
        tmp.forEach(el -> storage.get(findNominal(el)).remove(el));
        return tmp;
    }

    boolean checkCashExist(Map<Nominal, List<Integer>> storage, List<Integer> outCash) {
        List<Integer> tmp = new ArrayList<>(outCash);
        for (Integer el : tmp) {
            if (storage.containsKey(findNominal(el))) {
                return true;
            } else if (tryToDivide(storage, el)) return true;
        }
        return false;
    }

    private boolean tryToDivide(Map<Nominal, List<Integer>> storage, Integer el) {
        outCash.remove(el);
        outCash.addAll(divide(el));
        int sum = outCash.stream().mapToInt(integer -> integer).sum();
        int sum2 = storage.values().stream().flatMap(Collection::stream).mapToInt(elem -> elem).sum();
        return checkCashExist(storage, outCash) && sum == sum2;
    }

    private List<Integer> divide(int out) {
        if (out != 50) {
            if (out == 500) return Arrays.asList(200, 200, 100);
            if (out == 200) return Arrays.asList(100, 100);
            if (out == 100) return Arrays.asList(50, 50);
        }
        throw new AtmException("Невозможно выдать сумму данным номиналом.");
    }
}
