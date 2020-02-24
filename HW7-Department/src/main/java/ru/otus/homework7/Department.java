package ru.otus.homework7;

import java.util.Arrays;
import java.util.List;

public class Department {
    private final List<ATM> depAtm;

    public Department(MyATM...atm) {
        this.depAtm = Arrays.asList(atm);
    }

    int sumAtmCash() {
        BalanceATMAdder operation = new BalanceATMAdder();
        for (ATM el : depAtm) {
            el.accept(operation);
        }
        return operation.sum;
    }
}
