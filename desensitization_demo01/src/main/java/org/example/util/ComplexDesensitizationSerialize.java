package org.example.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.example.pojo.TestComplexPojo;

import java.io.IOException;

/**
 * @author Carson
 * @create 2024/11/27 星期三 下午 01:47
 */
public class ComplexDesensitizationSerialize extends JsonSerializer<TestComplexPojo> implements ContextualSerializer {
    @Override
    public void serialize(TestComplexPojo testComplexPojo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        return null;
    }
}
