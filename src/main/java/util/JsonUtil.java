package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;

public final class JsonUtil {
    private final static Logger logger = LogManager.getLogger(JsonUtil.class);

    private final static ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
    }

    @Nullable
    public static <T> T parseFromInputStream(InputStream inputStream, Class<T> typeClass) {
        try {
            return mapper.readValue(inputStream, typeClass);
        } catch (IOException ex) {
            logger.error("Invoke parseFromInputStream({}, {}) with exception.", inputStream, typeClass , ex);
        }
        return null;
    }

    @Nullable
    public static <T> String writeAsJsonString(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            logger.error("Invoke writeAsJsonString({}) with exception.", object, ex);
        }
        return null;
    }
}
