package com.wpx.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 不会飞的小鹏
 * @description： json转换工具类
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static String serializeObject(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("序列化异常");
        }
    }

    public static <T> T deserializeObject(String json, Class<T> target) {
        try {
            return mapper.readValue(json, target);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化异常");
        }
    }

    public static <T> T deserializeObject(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化异常");
        }
    }

    public static <T> List<T> deserializeList(String json, Class<T> target) {
        try {
            return mapper.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化异常");
        }
    }

    public static <T, R> Map<T, R> deserializeMap(String json, Class<T> keyTarget, Class<R> valueTarget) {
        try {
            return mapper.readValue(json, new TypeReference<Map<T, R>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化异常");
        }
    }

    public static <T> Map<T, T> deserializeMap(String json, Class<T> target) {
        try {
            return mapper.readValue(json, new TypeReference<Map<T, T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化异常");
        }
    }

}
