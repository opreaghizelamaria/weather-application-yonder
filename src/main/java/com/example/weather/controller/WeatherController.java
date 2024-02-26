package com.example.weather.controller;


import com.example.weather.service.WeatherService;
import lombok.AllArgsConstructor;
import org.openapitools.api.WeatherApi;
import org.openapitools.model.Result;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import java.util.List;

/**This is a rest controller that exposes an average temperature
 * and wind speed calculator endpoint */
@RestController
@AllArgsConstructor
public class WeatherController implements WeatherApi {

    private final WeatherService weatherService;

    /**
     *
     * @param cities  (required) a list of cities
     * @return Mono Result object containing weather information for the cities
     * The method below overrides getAverages method from WeatherApi interface generated
     * with openapi generator plugin
     */
    @Override
    public Mono<Result> getAverages(List<String> cities, ServerWebExchange serverWebExchange){
        return weatherService.generateResult(cities);
    }

}
