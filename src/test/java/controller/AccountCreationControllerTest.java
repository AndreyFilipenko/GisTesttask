package controller;

import org.junit.Test;
import org.mockito.internal.util.reflection.FieldSetter;
import service.AccountServiceImpl;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountCreationControllerTest {

    @Test
    public void doPostInternalError() throws IOException {
        AccountCreationController accountCreationController = new AccountCreationController();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        accountCreationController.doPost(req, resp);

        verify(resp, times(1)).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void doPostSuccessfulCreate() throws NoSuchFieldException, IOException {
        AccountServiceImpl accountService = mock(AccountServiceImpl.class);
        when(accountService.createAccount("name", "secondName")).thenReturn(true);
        String jsonString = "{" +
                "\"name\":\"name\"," +
                "\"secondName\":\"secondName\"" +
                "}";
        ServletInputStream servletInputStream = ServletInputStreamUtil.createMockServletInputStreamFromString(jsonString);

        AccountCreationController accountCreationController = new AccountCreationController();
        new FieldSetter(accountCreationController, accountCreationController.getClass().getDeclaredField("accountService")).set(accountService);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getInputStream()).thenReturn(servletInputStream);

        accountCreationController.doPost(req, resp);

        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
    }
}