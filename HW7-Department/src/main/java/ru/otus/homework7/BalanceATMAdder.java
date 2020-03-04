package ru.otus.homework7;

public class BalanceATMAdder implements OperationVisitor {
    public int sum;
    @Override
    public void visit(MyATM atm) {
        sum += atm.size();
    }
}
