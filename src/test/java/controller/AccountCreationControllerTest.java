package controller;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import service.AccountServiceImpl;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class AccountCreationControllerTest {

    @Test
    public void doPostInternalError() throws NoSuchFieldException, IOException {
        AccountServiceImpl accountService = mock(AccountServiceImpl.class);

        AccountCreationController accountCreationController = new AccountCreationController();
        new FieldSetter(accountCreationController, accountCreationController.getClass().getDeclaredField("accountService")).set(accountService);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        accountCreationController.doPost(req, resp);

        Mockito.verify(resp, times(1)).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void doPostSuccessfulCreate() throws NoSuchFieldException, IOException {
        AccountServiceImpl accountService = mock(AccountServiceImpl.class);
        Mockito.when(accountService.createAccount("name", "secondName")).thenReturn(true);
        String jsonString = "{" +
                "\"name\":\"name\"," +
                "\"secondName\":\"secondName\"" +
                "}";
        ServletInputStream servletInputStream = ServletInputStreamUtil.createMockServletInputStreamFromString(jsonString);

        AccountCreationController accountCreationController = new AccountCreationController();
        new FieldSetter(accountCreationController, accountCreationController.getClass().getDeclaredField("accountService")).set(accountService);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        Mockito.when(req.getInputStream()).thenReturn(servletInputStream);

        accountCreationController.doPost(req, resp);

        Mockito.verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
    }
}