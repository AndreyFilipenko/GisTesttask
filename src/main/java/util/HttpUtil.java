package util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public final class HttpUtil {
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String JSON_ENCODING_TYPE = StandardCharsets.UTF_8.name();

    private HttpUtil() {
    }

    public static <T> void writeJsonResponse(HttpServletResponse resp, T object) throws IOException {
        String json = JsonUtil.writeAsJsonString(object);
        PrintWriter out = resp.getWriter();
        resp.setContentType(JSON_CONTENT_TYPE);
        resp.setCharacterEncoding(JSON_ENCODING_TYPE);
        out.print(json);
        out.flush();
    }
}
