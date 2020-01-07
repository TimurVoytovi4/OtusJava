package ru.otus.homework6;

import java.util.Scanner;

public class ProgramInterfaceAtm {
    MyATM prostoBank = new MyATM();
    Scanner sc = new Scanner(System.in);

    void begin() {
        System.out.println("Укажите операцию введя число: ");
        System.out.println("1. Выдача наличных." + "\t" + "2. Пополнить." + "\n" + "3. Баланс."+ "\t" + "4. Завершить." );
        operations(sc.nextInt());
    }

    void operations(int i) {
        if (i == 1) {
            System.out.println("Укажите сумму: ");
            System.out.println("Вы получите средства следуюшими купюрами: " + prostoBank.get(sc.nextInt()));
            begin();
        } else if (i == 2) {
            System.out.println("Укажите сумму: ");
            prostoBank.set(sc.nextInt());
            begin();
        } else if (i == 3) {
            System.out.println("Баланс составляет: " + prostoBank.size());
            begin();
        }else if (i == 4) {
            System.out.println("Всего хорошего :)");
        } else System.out.println("Некорректное значение!");
    }
}
