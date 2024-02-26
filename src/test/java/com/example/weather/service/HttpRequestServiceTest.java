package com.example.weather.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;


public class HttpRequestServiceTest {
    private String BASE_URL = "https://998d8129-2264-4a98-a92e-ba8bde4a4d1c.mock.pstmn.io/";

    @Test
    void getDataForBucurestiTest(){
        WebTestClient
                .bindToServer()
                .baseUrl(BASE_URL)
                .build()
                .get()
                .uri("/Bucuresti")
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Disabled
    @Test
    void getDataForAradTest(){
        WebTestClient
                .bindToServer()
                .baseUrl(BASE_URL)
                .build()
                .get()
                .uri("/Arad")
                .exchange()
                .expectStatus().isOk();
    }
}
