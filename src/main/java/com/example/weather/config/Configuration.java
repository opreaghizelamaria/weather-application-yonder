package com.example.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Value("${forecast.url}")
    private String baseUrl;

    @Bean
    public WebClient webClient(){
        return WebClient.create(baseUrl);
    }

}
