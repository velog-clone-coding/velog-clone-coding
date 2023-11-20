package velog.sideProject.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMethod {
    private final static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());;

    public static String objectToJson(Object ob) {

        try {
            return objectMapper.writeValueAsString(ob);
        } catch (JsonProcessingException e) {
            log.info("json error = ", e);
        }
        return "";
    }

    // JSON 문자열을 객체로 변환
    public static <T> T jsonToObject(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            log.info("json error = ", e);
        }
        return null;
    }
}
