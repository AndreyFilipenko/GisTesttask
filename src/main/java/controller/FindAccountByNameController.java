package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.AccountNameDto;
import model.Account;
import service.AccountService;
import service.AccountServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/account/find-by-name")
public class FindAccountByNameController extends HttpServlet {
    private final AccountService service = AccountServiceFactory.getAccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AccountNameDto accountNameDto = mapper.readValue(req.getInputStream(), AccountNameDto.class);

        try {
            Account resultAccount = service.findAccountByName(accountNameDto.getName());
            if (resultAccount != null) {
                String accountJson = mapper.writeValueAsString(resultAccount);
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.print(accountJson);
                out.flush();
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException ex) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
