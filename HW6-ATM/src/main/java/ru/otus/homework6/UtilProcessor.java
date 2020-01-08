package ru.otus.homework6;

import java.util.*;

class UtilProcessor {
    List<Integer> outList = new ArrayList<>();


    void recognize(int cash) {
        if (cash >= 500)
            fillCash(500, cash);
        else if (cash >= 200)
            fillCash(200, cash);
        else if (cash >= 100)
            fillCash(100, cash);
        else if (cash >= 50)
            fillCash(50, cash);
        else throw new IllegalArgumentException();
    }

    private void fillCash(int nominal, int cash) {
        int k = cash % nominal;
        for (int j = cash / nominal; j > 0; j--) outList.add(nominal);
        if (k != 0) recognize(k);
    }

    Nominal findNominal(int cash) {
        return Arrays.stream(Nominal.values()).filter(var -> cash == Integer.parseInt(var.getTitle())).findFirst().orElse(null);
    }

    List<Integer> outFilledCash(Map<Nominal, Integer> storage, List<Integer> outCash) {
        for (var out : outCash) {
            int temp = out;
            for (var st : storage.entrySet()) {
                if ((int) st.getValue() == out) {
                    storage.remove(st.getKey());
                    temp = 0;
                }
            }
            if (temp != 0) {
                outCash.addAll(Objects.requireNonNull(divide(out)));
            }
        }
        return outList;
    }

    private List<Integer> divide(int out) {
        if (out == 500) return Arrays.asList(200, 200, 100);
        if (out == 200) return Arrays.asList(100, 100);
        if (out == 100) return Arrays.asList(50, 50);
        return null;
    }
}
