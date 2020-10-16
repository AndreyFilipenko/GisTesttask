package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.AccountDto;
import service.AccountService;
import service.AccountServiceFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account/create")
public class AccountCreationController extends HttpServlet {
    private final AccountService service = AccountServiceFactory.getAccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AccountDto accountDto = mapper.readValue(req.getInputStream(), AccountDto.class);

        boolean result = service.createAccount(accountDto.getName(), accountDto.getSecondName());
        if (result) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
