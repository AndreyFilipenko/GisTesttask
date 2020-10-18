package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountNameDtoTest {

    @Test
    public void fieldsGetTest() {
        String name = "randomName";
        AccountNameDto accountNameDto = new AccountNameDto(name);

        assertEquals(accountNameDto.getName(), name);
    }

}