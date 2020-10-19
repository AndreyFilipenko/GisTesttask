package controller;

import exception.RepositoryInternalException;
import model.Account;
import org.junit.Test;
import org.mockito.internal.util.reflection.FieldSetter;
import service.AccountServiceImpl;
import util.JsonUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountFindByNameControllerTest {

    @Test
    public void doGetInternalError() throws IOException {
        AccountFindByNameController accountFindByNameController = new AccountFindByNameController();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        accountFindByNameController.doGet(req, resp);

        verify(resp, times(1)).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void doGetSuccessful() throws RepositoryInternalException, NoSuchFieldException, IOException {
        AccountServiceImpl accountService = mock(AccountServiceImpl.class);
        Account account = new Account("name", "secondName");
        when(accountService.findAccountByName("name")).thenReturn(account);
        String jsonString = "{" +
                "\"name\":\"name\"" +
                "}";
        ServletInputStream servletInputStream = ServletInputStreamUtil.createMockServletInputStreamFromString(jsonString);
        PrintWriter printWriter = mock(PrintWriter.class);

        AccountFindByNameController accountFindByNameController = new AccountFindByNameController();
        new FieldSetter(accountFindByNameController, accountFindByNameController.getClass().getDeclaredField("accountService")).set(accountService);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getInputStream()).thenReturn(servletInputStream);
        when(resp.getWriter()).thenReturn(printWriter);

        accountFindByNameController.doGet(req, resp);

        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter, times(1)).print(JsonUtil.writeAsJsonString(account));
    }

    @Test
    public void doGetNotFound() throws RepositoryInternalException, NoSuchFieldException, IOException {
        AccountServiceImpl accountService = mock(AccountServiceImpl.class);
        when(accountService.findAccountByName("name")).thenReturn(null);
        String jsonString = "{" +
                "\"name\":\"name\"" +
                "}";
        ServletInputStream servletInputStream = ServletInputStreamUtil.createMockServletInputStreamFromString(jsonString);

        AccountFindByNameController accountFindByNameController = new AccountFindByNameController();
        new FieldSetter(accountFindByNameController, accountFindByNameController.getClass().getDeclaredField("accountService")).set(accountService);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getInputStream()).thenReturn(servletInputStream);

        accountFindByNameController.doGet(req, resp);

        verify(resp, times(1)).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}