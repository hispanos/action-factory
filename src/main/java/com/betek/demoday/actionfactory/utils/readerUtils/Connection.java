package com.betek.demoday.actionfactory.utils.readerUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class Connection {
    //@Value("${addressURL}")
    private String addressBaseUrl;

    public Connection() {
        this.addressBaseUrl = "http://localhost:8081";
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(this.addressBaseUrl).build();
    }
}
