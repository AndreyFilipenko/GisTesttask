package util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class HttpUtil {
    private HttpUtil() {
    }

    public static <T> void writeJsonResponse(HttpServletResponse resp, T object) throws IOException {
        String json = JsonUtil.writeAsJsonString(object);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}
