package ru.otus.homework6;

import ru.otus.homework6.Exceptions.AtmException;
import ru.otus.homework6.Exceptions.NotRecognized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AtmUtil {
    private List<Integer> temporaryOutCash = new ArrayList<>();

    List<Integer> recognize(int cash) {
        if (cash >= 500)
            return fillCash(temporaryOutCash, 500, cash);
        else if (cash >= 200)
            return fillCash(temporaryOutCash, 200, cash);
        else if (cash >= 100)
            return fillCash(temporaryOutCash, 100, cash);
        else if (cash >= 50)
            return fillCash(temporaryOutCash, 50, cash);
        else throw new NotRecognized(cash);
    }

    List<Integer> divide(int cash) {
        if (cash != 50) {
            if (cash == 500) return Arrays.asList(200, 200, 100);
            if (cash == 200) return Arrays.asList(100, 100);
            if (cash == 100) return Arrays.asList(50, 50);
        }
        throw new AtmException("Невозможно выдать данную сумму.");
    }

    List<Integer> fillCash(List<Integer> tmp, int nominal, int cash) {
        int k = cash % nominal;
        for (int j = cash / nominal; j > 0; j--) tmp.add(nominal);
        if (k != 0) recognize(k);
        return tmp;
    }
}
