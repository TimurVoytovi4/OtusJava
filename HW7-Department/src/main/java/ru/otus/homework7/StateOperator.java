package ru.otus.homework7;

import java.util.*;

public class StateOperator implements OperationVisitor {
    Map<ATM, AtmSnapshot> snapshotMap = new HashMap<>();
    AtmSnapshot snapshot;

    @Override
    public void visit(MyATM atm) {
        snapshot = new AtmSnapshot(
                new ArrayList<>(atm.fiveHundredsCell),
                new ArrayList<>(atm.twoHundredsCell),
                new ArrayList<>(atm.oneHundredsCell),
                new ArrayList<>(atm.fiftiesCell));
        snapshotMap.put(atm, snapshot);
    }

    public Map<Nominal, List<Nominal>> load(ATM atm) {
        return snapshotMap.get(atm).storage;
    }

    static class AtmSnapshot {
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
    }
}