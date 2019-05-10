package ru.otus.homework2;

import java.util.Collections;

class TestDIY {
    public static void main(String[] args) {
        String testWord = "Test";
        DIYarratList<String> diYarrayList = new DIYarratList<>();
        diYarrayList.add(testWord + "965");
        diYarrayList.add(testWord + "965");
        DIYarratList<String> diYarrayList2 = new DIYarratList<>();
        diYarrayList2.add(testWord + "698");
        diYarrayList2.add(testWord + "698");
        Collections.copy(diYarrayList2, diYarrayList);
        for (int i = 0; i < 21; i++) {
            diYarrayList.add(testWord + i);
        }
        Collections.addAll(diYarrayList, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21");
        Collections.sort(diYarrayList);
        for (String str : diYarrayList)
            System.out.print(str + " ");

        System.out.println();
        for (String str : diYarrayList2)
            System.out.print(str + " ");
    }
}
