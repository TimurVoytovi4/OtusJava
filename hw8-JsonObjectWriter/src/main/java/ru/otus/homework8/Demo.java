package ru.otus.homework8;

import java.util.Arrays;
import java.util.Map;

public class Demo {
    public static void main(String[] args){
        MyJson json = new MyJson();

        User[] users = {
                new User("ivan1", "petrov", Arrays.asList(12, 86, 95), Map.of("Tel",322223,"icq",654654)),
                new User("ivan2", "suslikov", Arrays.asList(12, 86, 95), Map.of("Tel",322223,"icq",654654)),
                new User("ivan3", "pupkin", Arrays.asList(12, 86, 95), Map.of("Tel",322223,"icq",654654)),
                new User("ivan4", "hvostikov", Arrays.asList(12, 86, 95), Map.of("Tel",322223,"icq",654654))};

        json.toJson(users);
    }
}
