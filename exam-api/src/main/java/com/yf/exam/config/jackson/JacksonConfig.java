package com.yf.exam.config.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JSON序列化配置
 * @author van
 */
@Configuration
public class JacksonConfig {

    @Bean
    public SimpleModule xssEscapeModule() {
        SimpleModule module = new SimpleModule();
        // 注册反序列化器（处理输入）
        module.addDeserializer(String.class, new XssEscapeJsonDeserializer());
        return module;
    }
}
