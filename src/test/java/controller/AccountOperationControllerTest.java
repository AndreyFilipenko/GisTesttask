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

public class AccountOperationControllerTest {

    @Test
    public void doPutInternalError() throws IOException {
        AccountOperationController accountOperationController = new AccountOperationController();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        accountOperationController.doPut(req, resp);

        verify(resp, times(1)).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void doPutSuccessfulCreate() throws NoSuchFieldException, IOException {
        AccountServiceImpl accountService = mock(AccountServiceImpl.class);
        when(accountService.updateAccountSecondName("name", "secondName")).thenReturn(true);
        String jsonString = "{" +
                "\"name\":\"name\"," +
                "\"secondName\":\"secondName\"" +
                "}";
        ServletInputStream servletInputStream = ServletInputStreamUtil.createMockServletInputStreamFromString(jsonString);

        AccountOperationController accountOperationController = new AccountOperationController();
        new FieldSetter(accountOperationController, accountOperationController.getClass().getDeclaredField("accountService")).set(accountService);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getInputStream()).thenReturn(servletInputStream);

        accountOperationController.doPut(req, resp);

        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
    }
}