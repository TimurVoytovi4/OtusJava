package ru.otus.homework6;

import java.util.List;

public interface ATM {
    void set(int cash);
    List<Integer> get(int cash);
    int size();
}
