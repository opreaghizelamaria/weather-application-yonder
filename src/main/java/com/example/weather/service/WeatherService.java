package com.example.weather.service;

import lombok.AllArgsConstructor;
import org.openapitools.model.Result;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;

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
