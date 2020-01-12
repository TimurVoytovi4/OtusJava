package ru.otus.homework6;

import ru.otus.homework6.Exceptions.AtmException;
import ru.otus.homework6.Exceptions.NotRecognized;

import java.util.*;

class AtmUtil {

    List<Integer> temporaryOutCash = new ArrayList<>();

    List<Integer> recognize(int cash) {
        for (var nominal : Nominal.values()) {
            int value = nominal.getTitle();
            if (cash >= value) {
                    for (int j = cash / value; j > 0; j--) temporaryOutCash.add(value);
                    if (cash % value != 0) recognize(cash % value);
                    return temporaryOutCash;
            }
        }
        throw new NotRecognized(cash);
    }

    List<Integer> divide(int cash) {
        if (cash!=50) {
            List<Integer> tmp = new ArrayList<>();
            for (var nominal : Nominal.values()) {
                int value = nominal.getTitle();
                if (cash == value) {
                    int nextValue = Arrays.asList(Nominal.values()).get(nominal.ordinal() + 1).getTitle();
                    for (int j = cash / nextValue; j > 0; j--) tmp.add(nextValue);
                    if (cash % value != 0) tmp.add(cash % value);
                    return tmp;
                }
            }
        }
        throw new AtmException("Невозможно выдать данную сумму.");
    }

}
