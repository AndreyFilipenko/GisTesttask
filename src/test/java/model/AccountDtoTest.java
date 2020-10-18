package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDtoTest {

    @Test
    public void fieldsGetTest() {
        String name = "randomName";
        String secondName = "randomSecondName";
        AccountDto accountDto = new AccountDto(name, secondName);

        assertEquals(accountDto.getName(), name);
        assertEquals(accountDto.getSecondName(), secondName);
    }

}