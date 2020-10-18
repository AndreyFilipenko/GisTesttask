package util;

import model.AccountDto;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class JsonUtilTest {

    @Test
    public void parseFromInputStreamReturnMappedObject() {
        String jsonString = "{" +
                "\"name\":\"name\"," +
                "\"secondName\":\"secondName\"" +
                "}";
        InputStream inputStream = new ByteArrayInputStream(jsonString.getBytes());
        assertEquals(new AccountDto("name", "secondName"), JsonUtil.parseFromInputStream(inputStream, AccountDto.class));
    }

    @Test
    public void parseFromInputStreamReturnNull() {
        String invalidJsonString = "invalid json";
        InputStream inputStream = new ByteArrayInputStream(invalidJsonString.getBytes());
        assertNull(JsonUtil.parseFromInputStream(inputStream, AccountDto.class));
    }

    @Test
    public void writeAsJsonStringReturnJson() {
        String jsonString = "{" +
                "\"name\":\"name\"," +
                "\"secondName\":\"secondName\"" +
                "}";
        assertEquals(jsonString, JsonUtil.writeAsJsonString(new AccountDto("name", "secondName")));
    }

    @Test
    public void writeAsJsonStringReturnEmptyJson() {
        assertEquals(JsonUtil.EMPTY_JSON, JsonUtil.writeAsJsonString(null));
    }

}