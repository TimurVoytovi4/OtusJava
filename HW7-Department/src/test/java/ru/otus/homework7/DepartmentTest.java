package ru.otus.homework7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.otus.homework7.Exceptions.AtmException;
import ru.otus.homework7.Exceptions.NotRecognized;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    Department department = new Department(new MyATM(), new MyATM());
    @BeforeEach
     void beforeAll() {
        department.getDepAtm(0).set(100);
        department.getDepAtm(0).set(100);
        department.getDepAtm(0).set(100);

        department.getDepAtm(1).set(100);
        department.getDepAtm(1).set(100);
        department.getDepAtm(1).set(100);
        department.getDepAtm(1).set(100);
    }

    @Test
    void sumAtmCash() {
        assertEquals(department.sumAtmCash(), 700);
    }

    @Test
    void saveLoadState() {
        department.saveState(department.getDepAtm(0));
        department.saveState(department.getDepAtm(1));
        department.getDepAtm(0).set(200);
        department.getDepAtm(1).set(100);
        assertEquals(department.sumAtmCash(), 1000);

        department.restoreState((MyATM) department.getDepAtm(0));
        department.restoreState((MyATM) department.getDepAtm(1));

        assertEquals(department.sumAtmCash(), 700);
    }

    @Nested
    class MyATMTest{
        ATM atm;

        @BeforeEach
        void setUp() {
            atm = department.getDepAtm(0);
        }

        @Test
        void size() {
            assertEquals(atm.size(), 300);
        }

        @Test
        void setAtm() {
            atm.set(50);
            assertEquals(atm.size(), 350);
        }

        @Test
        void moreSetAtm() {
            atm.set(500);
            atm.set(500);
            atm.set(200);
            atm.set(200);
            atm.set(50);
            assertEquals(atm.size(), 1750);
        }

        @Test
        void wrongSetAtm() {
            Exception exception = assertThrows(NotRecognized.class, () -> atm.set(30));
            assertEquals("Не распознана данная купюра: 30", exception.getMessage());
            assertTrue(exception.getMessage().contains("Не распознана данная купюра: 30"));
            assertNotEquals(atm.size(), 330);
        }

        @Test
        void wrongGetAtm() {
            Exception exception = assertThrows(AtmException.class, () -> atm.get(3500));
            assertEquals("Невозможно выдать данную сумму.", exception.getMessage());
            assertTrue(exception.getMessage().contains("Невозможно выдать данную сумму."));
        }

        @Test
        void get() {
            atm.get(300);
            assertEquals(atm.size(), 0);
        }
    }
}