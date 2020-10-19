package util;

import model.Account;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HttpUtilTest {

    @Test
    public void writeJsonResponse() throws IOException {
        Account account = new Account("name", "secondName");
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(printWriter);

        HttpUtil.writeJsonResponse(resp, account);
        verify(resp, times(1)).setContentType(HttpUtil.JSON_CONTENT_TYPE);
        verify(resp, times(1)).setCharacterEncoding(HttpUtil.JSON_ENCODING_TYPE);
        verify(printWriter, times(1)).print(JsonUtil.writeAsJsonString(account));
    }
}