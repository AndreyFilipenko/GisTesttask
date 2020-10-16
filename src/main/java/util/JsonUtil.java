package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public final class JsonUtil {
    private final static ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static <T> T parseFromInputStream(InputStream inputStream, Class<T> typeClass) throws IOException {
        return mapper.readValue(inputStream, typeClass);
    }

    public static <T> String writeAsJsonString(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
