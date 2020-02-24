package ru.otus.homework7;

import ru.otus.homework7.Exceptions.AtmException;
import ru.otus.homework7.Exceptions.NotRecognized;
import ru.otus.homework7.ATM;
import ru.otus.homework7.MyATM;

import java.util.Scanner;

public class AtmUserInterface {
    ATM myATM = new MyATM();
    Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("Укажите операцию введя число: ");
        System.out.println("1. Выдача наличных." + "\t" + "2. Пополнить." + "\n" + "3. Баланс." + "\t" + "4. Завершить.");
        operations(sc.nextInt());
    }

    void operations(int i) {
        if (i == 1) {
            System.out.println("Укажите сумму: ");
            try {
                System.out.println("Вы получите средства следуюшими купюрами: " + myATM.get(sc.nextInt()));
            } catch (AtmException e) {
                System.out.println(e.getMessage());
            }
            start();
        } else if (i == 2) {
            try {
                System.out.println("Внесите купюру(принимаются номиналы:500, 200, 100, 50): ");
                myATM.set(sc.nextInt());
            } catch (NotRecognized e) {
                System.out.println(e.getMessage());
            }
            start();
        } else if (i == 3) {
            System.out.println("Баланс составляет: " + myATM.size());
            start();
        } else if (i == 4) {
            System.out.println("Всего хорошего :)");
        } else System.out.println("Некорректное значение!");
    }
}
