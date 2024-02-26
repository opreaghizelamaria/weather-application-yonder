package com.example.weather.service;

import com.example.weather.domain.CityForecast;
import lombok.AllArgsConstructor;
import org.openapitools.model.City;
import org.openapitools.model.Result;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

import static com.example.weather.util.Constants.FORECAST_CITIES;

@Service
@AllArgsConstructor
/*This service generates the result object and sends it to CsvWriterService */
public class WeatherService {

    private final CsvWriterService csvWriterService;
    private final PopulateResultService populateResultService;

    /**
     *
     * @param cities list of cities from the api call
     * @return Mono<Result> object to the controller
     * gets the populated response object, sends it to CsvWriterService and returns it to the
     * controller
     */
    public Mono<Result> generateResult(List<String> cities){
        Mono<Result> r = populateResultService.populateResultObject(cities);
        csvWriterService.writeResultToCsv(r);
        return r;
    }


}
