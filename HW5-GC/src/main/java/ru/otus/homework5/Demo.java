package ru.otus.homework5;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        ObjectProducer leakMem = new ObjectProducer(100000000);
        leakMem.run();
    }
}
