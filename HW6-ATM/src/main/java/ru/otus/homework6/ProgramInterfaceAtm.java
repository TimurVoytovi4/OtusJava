package ru.otus.homework6;

import ru.otus.homework6.Exceptions.AtmException;

import java.util.Scanner;

public class ProgramInterfaceAtm {
    ATM prostoBank = new MyATM();
    Scanner sc = new Scanner(System.in);

    void begin() {
        System.out.println("Укажите операцию введя число: ");
        System.out.println("1. Выдача наличных." + "\t" + "2. Пополнить." + "\n" + "3. Баланс." + "\t" + "4. Завершить.");
        operations(sc.nextInt());
    }

    void operations(int i) {
        if (i == 1) {
            System.out.println("Укажите сумму: ");
            try {
                System.out.println("Вы получите средства следуюшими купюрами: " + prostoBank.get(sc.nextInt()));
            } catch (AtmException e) {
                System.out.println(e.getMessage());
            }
            begin();
        } else if (i == 2) {
            System.out.println("Внесите купюру(принимаются номиналы:500, 200, 100, 50): ");
            prostoBank.set(sc.nextInt());
            begin();
        } else if (i == 3) {
            System.out.println("Баланс составляет: " + prostoBank.size());
            begin();
        } else if (i == 4) {
            System.out.println("Всего хорошего :)");
        } else System.out.println("Некорректное значение!");
    }
}
