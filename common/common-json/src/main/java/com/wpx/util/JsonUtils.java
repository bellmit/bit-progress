package com.wpx.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * json转换工具类
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 将对象序列化为json字符串
     *
     * @param o 需要序列化的对象
     * @return json字符串
     */
    public static String serializeObject(Object o) {
        if (Objects.isNull(o)) {
            return "{}";
        }
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            logger.error("serializeObject [{}] toJsonStr errorMessage [{}] ", o, e.getMessage(), e);
            throw new RuntimeException("序列化异常");
        }
    }

    public static <T> T deserializeObject(String json, Class<T> target) {
        try {
            return mapper.readValue(json, target);
        } catch (IOException e) {
            logger.error("deserialize jsonStr [{}] toObject errorMessage [{}] ", json, e.getMessage(), e);
            throw new RuntimeException("反序列化异常");
        }
    }

    public static <T> T deserializeObject(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.error("deserialize jsonStr [{}] toObject errorMessage [{}] ", json, e.getMessage(), e);
            throw new RuntimeException("反序列化异常");
        }
    }

    public static <T> List<T> deserializeList(String json, Class<T> target) {
        try {
            return mapper.readValue(json, new TypeReference<List<T>>() {});
        } catch (IOException e) {
            logger.error("deserialize jsonStr [{}] toList errorMessage [{}] ", json, e.getMessage(), e);
            throw new RuntimeException("反序列化异常");
        }
    }

    public static <T> Set<T> deserializeSet(String json, Class<T> target) {
        try {
            return mapper.readValue(json, new TypeReference<Set<T>>() {});
        } catch (IOException e) {
            logger.error("deserialize jsonStr [{}] toSet errorMessage [{}] ", json, e.getMessage(), e);
            throw new RuntimeException("反序列化异常");
        }
    }

    public static <T> Map<T, T> deserializeMap(String json, Class<T> target) {
        return deserializeMap(json, target, target);
    }

    public static <T, R> Map<T, R> deserializeMap(String json, Class<T> keyTarget, Class<R> valueTarget) {
        try {
            return mapper.readValue(json, new TypeReference<Map<T, R>>() {});
        } catch (IOException e) {
            logger.error("deserialize jsonStr [{}] toMap errorMessage [{}] ", json, e.getMessage(), e);
            throw new RuntimeException("反序列化异常");
        }
    }

}
