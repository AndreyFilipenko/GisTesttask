package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void fieldsGetTest() {
        String name = "randomName";
        String secondName = "randomSecondName";
        Account account = new Account(name, secondName);

        assertEquals(account.getName(), name);
        assertEquals(account.getSecondName(), secondName);
    }
}