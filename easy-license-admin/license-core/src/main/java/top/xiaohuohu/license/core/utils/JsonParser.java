package top.xiaohuohu.license.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jackson 工具
 */
public class JsonParser {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * TypeReference json 转 bean
     *
     * @param json         json 字符串
     * @param valueTypeRef bean type
     * @return bean
     */
    public static <T> T parse(String json, TypeReference<T> valueTypeRef) {
        try {
            return MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new IllegalArgumentException("无法转换json至bean", e);
        }
    }

    /**
     * Class json字符串 转 bean
     *
     * @param json   json 字符串
     * @param tClass bean class
     * @return bean
     */
    public static <T> T parse(String json, Class<T> tClass) {
        try {
            return MAPPER.readValue(json, tClass);
        } catch (Exception e) {
            throw new IllegalArgumentException("无法转换json至bean", e);
        }
    }

    /**
     * bean 转 json
     *
     * @param obj bean
     * @return json
     */
    public static <T> String unParse(T obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("无法转换bean至json", e);
        }
    }

    /**
     * bean 转 json 带格式化美化
     *
     * @param obj bean
     * @return json
     */
    public static <T> String unParseBeautify(T obj) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("无法转换bean至json", e);
        }
    }
}
