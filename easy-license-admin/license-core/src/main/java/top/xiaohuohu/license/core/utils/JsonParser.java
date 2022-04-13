package top.xiaohuohu.license.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jackson 工具
 */
public class JsonParser {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T parse(String json, TypeReference<T> valueTypeRef) {
        try {
            return MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse json", e);
        }
    }

    public static <T> T parse(String json, Class<T> tClass) {
        try {
            return MAPPER.readValue(json, tClass);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse json", e);
        }
    }

    public static <T> String unParse(T obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot unParse json", e);
        }
    }

    public static <T> String unParseBeautify(T obj) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot unParseBeautify json", e);
        }
    }
}
