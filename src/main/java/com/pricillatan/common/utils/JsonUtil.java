package com.pricillatan.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class JsonUtil {

    private static Map<String, ObjectMapper> cacheObjectMapper = new ConcurrentHashMap<>();

    public static ObjectMapper getObjectMapper(Class clazz) {
        ObjectMapper objMapper = cacheObjectMapper.computeIfAbsent(clazz.getName(), k -> new ObjectMapper());
        return objMapper;
    }

    public static byte[] toJsonByte(Object obj) throws IOException {
        ObjectMapper objectMapper = JsonUtil.getObjectMapper(obj.getClass());
        return objectMapper.writeValueAsBytes(obj);
    }

    public static <T> T fromJsonByte(byte[] json, Class<T> classInst) throws IOException {
        ObjectMapper objectMapper = JsonUtil.getObjectMapper(classInst);
        return objectMapper.readValue(json, classInst);
    }

    public static String toJson(Object obj) throws IOException {
        ObjectMapper objectMapper = JsonUtil.getObjectMapper(obj.getClass());
        return toJson(objectMapper, obj);
    }

    public static String toJson(ObjectMapper objectMapper, Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T fromJson(InputStream is, Class<T> classInst) throws IOException {
        ObjectMapper objectMapper = JsonUtil.getObjectMapper(classInst);
        return objectMapper.readValue(is, classInst);
    }

    public static <T> T fromJson(String json, Class<T> classInst) throws IOException {
        ObjectMapper objectMapper = JsonUtil.getObjectMapper(classInst);
        return objectMapper.readValue(json, classInst);
    }

    public static <T> T convObj(Object obj, Class<T> objClass) throws IOException {
        //TODO
        ObjectMapper objMapper = JsonUtil.getObjectMapper(objClass);
        ObjectMapper inputObjMapper = JsonUtil.getObjectMapper(obj.getClass());
        return (T) objMapper.readValue(inputObjMapper.writeValueAsBytes(obj), objClass);

    }

    public static <T> List<T> convJson2List(String json, Class<T> objClass) throws IOException {
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.readValue(json, objMapper.getTypeFactory().constructCollectionType(List.class, objClass));
    }

    public static <T> List<T> convList(Object obj, Class<T> objClass) throws IOException {
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.readValue(objMapper.writeValueAsBytes(obj), objMapper.getTypeFactory().constructCollectionType(List.class, objClass));
    }


    public static <T, T2> Map<T, T2> convJson2Map(String json, Class<T> keyClass, Class<T2> valueClass) throws IOException {
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.readValue(json, objMapper.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass));
    }

}
