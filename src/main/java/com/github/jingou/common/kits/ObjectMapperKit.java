package com.github.jingou.common.kits;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * @author caedmon
 */
public class ObjectMapperKit {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 将json字符串转化成java类
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T toObject(String json, Class<T> clz) throws IOException {
        if (null == json || json.trim().isEmpty()) {
            return null;
        }
        T t = null;
        t = objectMapper.readValue(json, clz);
        return t;
    }

    /**
     * 将输入流转换成java类
     *
     * @param is
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toObject(InputStream is, Class<T> clazz) throws IOException {
        T t = null;
        t = objectMapper.readValue(is, clazz);
        return t;
    }

    /**
     * 将java类转换成json字符串
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        StringWriter sw = new StringWriter();
        try {
            objectMapper.writeValue(sw, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

}
