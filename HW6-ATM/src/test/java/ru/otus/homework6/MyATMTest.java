package ru.otus.homework6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework6.Exceptions.AtmException;
import ru.otus.homework6.Exceptions.NotRecognized;

import static org.junit.jupiter.api.Assertions.*;


class MyATMTest {

    ATM atm = new MyATM();

    @BeforeEach
    void setUp() {
        atm.set(100);
        atm.set(100);
        atm.set(100);
    }

    @Test
    void size(){
        assertEquals(atm.size(), 300);
    }

    @Test
    void setAtm(){
        atm.set(50);
        assertEquals(atm.size(), 350);
    }

    @Test
    void moreSetAtm(){
        atm.set(500);
        atm.set(500);
        atm.set(200);
        atm.set(200);
        atm.set(50);
        assertEquals(atm.size(), 1750);
    }

    @Test
    void wrongSetAtm(){
        Exception exception = assertThrows(NotRecognized.class,() ->atm.set(30));
        assertEquals("Не распознана данная купюра: 30", exception.getMessage());
        assertTrue(exception.getMessage().contains("Не распознана данная купюра: 30"));
        assertNotEquals(atm.size(), 330);
    }

    @Test
    void wrongGetAtm(){
        Exception exception = assertThrows(AtmException.class,() ->atm.get(3500));
        assertEquals("Невозможно выдать данную сумму.", exception.getMessage());
        assertTrue(exception.getMessage().contains("Невозможно выдать данную сумму."));
    }

    @Test
    void get(){
        atm.get(300);
        assertEquals(atm.size(), 0);
    }
}