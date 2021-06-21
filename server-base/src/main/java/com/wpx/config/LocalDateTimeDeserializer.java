package com.wpx.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Resource(name = "localDateTimeToTimestampConverter")
    private Converter<String, LocalDateTime> localDateTimeConverter;

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        return localDateTimeConverter.convert(jsonParser.getText());
    }

    @Override
    public Class<?> handledType() {
        return LocalDateTime.class;
    }
}
