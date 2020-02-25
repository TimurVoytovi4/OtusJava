package ru.otus.homework7;

import java.util.*;

public class StateOperator implements OperationVisitor {
    private AtmSnapshot snapshot;
    @Override
    public void visit(MyATM atm) {
        this.snapshot = new AtmSnapshot(
                atm.fiveHundredsCell,
                atm.twoHundredsCell,
                atm.oneHundredsCell,
                atm.fiftiesCell);
    }

    public ATM load() {
        return snapshot.getStorage();
    }

    static class AtmSnapshot{
        private Map<Nominal, List<Nominal>> storage;
        public AtmSnapshot(List<Nominal> fiveHundredsCell,
                           List<Nominal> twoHundredsCell,
                           List<Nominal> oneHundredsCell,
                           List<Nominal> fiftiesCell) {
            this.storage = new EnumMap<>(Nominal.class);
            Arrays.stream(Nominal.values()).forEach(var -> storage.put(var, Arrays.asList(fiveHundredsCell,
                    twoHundredsCell,
                    oneHundredsCell,
                    fiftiesCell).get(var.ordinal())));
        }

        public ATM getStorage() {
            return (MyATM) storage;
        }
    }
}