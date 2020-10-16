package controller;

import exception.RepositoryInternalException;
import model.AccountNameDto;
import model.Account;
import service.AccountService;
import service.AccountServiceFactory;
import util.HttpUtil;
import util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.*;

@WebServlet("/account/find-by-name")
public class AccountFindByNameController extends HttpServlet {
    private final AccountService service = AccountServiceFactory.getAccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AccountNameDto accountNameDto = JsonUtil.parseFromInputStream(req.getInputStream(), AccountNameDto.class);

        try {
            Account resultAccount = service.findAccountByName(accountNameDto.getName());
            if (resultAccount != null) {
                HttpUtil.writeJsonResponse(resp, resultAccount);
                resp.setStatus(SC_OK);
            } else {
                resp.setStatus(SC_NOT_FOUND);
            }
        } catch (RepositoryInternalException ex) {
            resp.setStatus(SC_INTERNAL_SERVER_ERROR);
        }
    }
}