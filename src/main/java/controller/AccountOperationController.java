package controller;

import model.AccountDto;
import service.AccountService;
import service.AccountServiceFactory;
import util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@WebServlet("/account/update")
public class AccountOperationController extends HttpServlet {
    private final AccountService accountService = AccountServiceFactory.getAccountService();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AccountDto accountDto = JsonUtil.parseFromInputStream(req.getInputStream(), AccountDto.class);
        if (accountDto == null) {
            resp.setStatus(SC_INTERNAL_SERVER_ERROR);
            return;
        }

        boolean result = accountService.updateAccountSecondName(accountDto.getName(), accountDto.getSecondName());
        resp.setStatus(result ? SC_OK : SC_INTERNAL_SERVER_ERROR);
    }
}
