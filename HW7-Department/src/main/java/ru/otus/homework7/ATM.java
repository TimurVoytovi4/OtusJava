package ru.otus.homework7;

import java.util.List;

public interface ATM extends Component {
    void set(int cash);
    List<Integer> get(int cash);
    int size();
}
