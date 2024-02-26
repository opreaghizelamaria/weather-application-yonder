package com.example.weather.service;

import com.example.weather.domain.CityForecast;
import com.example.weather.domain.WeatherInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
/* This service creates http requests */
public class HttpRequestService {
    private final WebClient webClient;

    /**
     *
     * @param cities contains the name of the cities for which data needs to be collected
     * @return a flux of forecast data for each city
     */
    public Flux<CityForecast> getForecastInformation(List<String> cities){
        return Flux.fromIterable(cities)
                .flatMap(this::getCity);    }

    /**
     *
     * @param city determines the city for which the api call is made
     * @return a Mono<CityForecast> object representing the city name and its forecast
     * The method below uses WebClient interface to send HTTP requests
     */
    private Mono<CityForecast> getCity(String city) {
      return webClient.get()
                .uri("{city}", city)
                .retrieve()
                .bodyToMono(WeatherInformation.class)
              .onErrorResume(WebClientResponseException.class,
                      ex -> Mono.just(new WeatherInformation()))
              .flatMap(weatherInformation -> Mono.just(new CityForecast(city, weatherInformation)));
    }

}
