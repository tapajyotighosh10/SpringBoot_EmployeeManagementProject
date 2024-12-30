package com.cbnits.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        // Customize the ObjectMapper if needed
        return Jackson2ObjectMapperBuilder.json().indentOutput(true).build();
    }
}
