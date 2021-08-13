package com.wpx.config;

import com.wpx.util.LocalDateTimeUtils;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * @author 不会飞的小鹏
 */
@Configuration
public class FeignFormatterRegister implements FeignFormatterRegistrar {

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateTimeFormatter());
    }

    public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

        @Override
        public LocalDateTime parse(String text, Locale locale) {
            return LocalDateTime.parse(text);
        }

        @Override
        public String print(LocalDateTime date, Locale locale) {
            return String.valueOf(LocalDateTimeUtils.toMilliseconds(date));
        }
    }
}