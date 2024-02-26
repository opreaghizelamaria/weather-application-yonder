package com.example.weather;

import com.example.weather.controller.WeatherController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openapitools.model.City;
import org.openapitools.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;

@RunWith(SpringRunner.class)
@ComponentScan
@WebFluxTest(WeatherController.class)
public class WeatherControllerITest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getAveragesEndpointTest(){
        Flux<Result> resultMono =
                webTestClient.get().uri("/api/weather?city=Constanta,Bucuresti,Oradea,Arad")
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .returnResult(Result.class)
                        .getResponseBody();


        Result result = new Result();
        result.addResultItem(new City("Arad","",""));
        result.addResultItem(new City("Bucuresti","",""));
        result.addResultItem(new City("Constanta","",""));
        StepVerifier.create(resultMono)
                .expectSubscription()
                .expectNext(result)
                .verifyComplete();
    }
}
