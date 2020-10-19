package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Matchers;
import org.mockito.stubbing.Answer;

import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServletInputStreamUtil {
    private static final Logger logger = LogManager.getLogger(ServletInputStreamUtil.class);

    public static ServletInputStream createMockServletInputStreamFromString(String value) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(value.getBytes());

        ServletInputStream servletInputStream = mock(ServletInputStream.class);

        try {
            when(servletInputStream.read(Matchers.any(), anyInt(), anyInt())).thenAnswer((Answer<Integer>) invocationOnMock -> {
                Object[] args = invocationOnMock.getArguments();
                byte[] output = (byte[]) args[0];
                int offset = (int) args[1];
                int length = (int) args[2];
                return byteArrayInputStream.read(output, offset, length);
            });
        } catch (IOException ex) {
            logger.error("Invoke createMockServletInputStreamFromString({}) with exception.", value, ex);
        }
        return servletInputStream;
    }
}
