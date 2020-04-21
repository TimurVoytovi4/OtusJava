package ru.otus.homework8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
public class User implements Serializable {
    private final String name;
    private final String lastName;
    private final List<Integer> parameters;
    private final Map<String, Integer> contacts;
}
