package ru.otus.homework6;

import ru.otus.homework6.Exceptions.AtmException;
import ru.otus.homework6.Exceptions.NotRecognized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AtmUtil {
    AtmUtil() {
    }

    Nominal findNominal(int cash) {
        return Arrays.stream(Nominal.values()).filter(var -> cash == var.getTitle()).findFirst().orElse(null);
    }

    List<Integer> divide(int cash) {
        List<Integer> tmp = new ArrayList<>();
        for (var nominal : Nominal.values()) {
            int value = nominal.getTitle();
            if (cash >= value) {
                if (cash != value) {
                    return fillList(cash, tmp, value);
                } else if (cash != 50) {
                    int nextValue = Arrays.asList(Nominal.values()).get(nominal.ordinal() + 1).getTitle();
                    return fillList(cash, tmp, nextValue);
                }
                throw new AtmException("Невозможно выдать данную сумму.");
            }
        }
        throw new NotRecognized(cash);
    }

    List<Integer> fillList(int cash, List<Integer> tmp, int value) {
        for (int j = cash / value; j > 0; j--) tmp.add(value);
        if (cash % value != 0) tmp.add(cash % value);
        return tmp;
    }
}