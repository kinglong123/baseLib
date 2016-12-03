package com.kinglong.data;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ObjectMapperUtils
 *
 * @author lanjl
 * @version 2016/12/2
 */
public class ObjectMapperUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static ObjectMapper getMapperInstance() {
        return OBJECT_MAPPER;
    }

    public static JavaType constructParametricType(Class<?> collectionClass, Class<?>... elementClasses) {
        return OBJECT_MAPPER.getTypeFactory()
                .constructParametricType(collectionClass, elementClasses);
    }
}
