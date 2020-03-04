package ru.otus.homework7;

import java.util.Arrays;
import java.util.List;

public class Department {
    private final List<ATM> depAtm;
    private final StateOperator stateOperator = new StateOperator();


    public ATM getDepAtm(int i) {
        return depAtm.get(i);
    }

    public Department(ATM... atm) {
        this.depAtm = Arrays.asList(atm);
    }

    int sumAtmCash() {
        BalanceATMAdder operation = new BalanceATMAdder();
        for (ATM el : depAtm) {
            el.accept(operation);
        }
        return operation.sum;
    }

    void saveState(ATM atm) {
        atm.accept(stateOperator);
    }

    void restoreState(MyATM atm){
        atm.setStorage(stateOperator.restore(atm));
    }
}
