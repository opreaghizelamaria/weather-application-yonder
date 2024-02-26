package com.example.weather.service;

import com.example.weather.domain.CityForecast;
import lombok.AllArgsConstructor;
import org.openapitools.model.City;
import org.openapitools.model.Result;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.weather.util.Constants.FORECAST_CITIES;

@Service
@AllArgsConstructor
/* This service populates the Mono Result object*/
public class PopulateResultService {
    private final HttpRequestService httpRequestService;
    private final AverageCalculatorService averageCalculatorService;
    private static Result result;
    /**
     *
     * @param cities list of cities from the api getAverages call
     * @return the Result object wrapped into Mono
     */
    public Mono<Result> populateResultObject(List<String> cities){
        result = new Result();
        return getApiCallResponses(cities).map(c -> {
                result.addResultItem(getPopulatedCityObject(c));
                return result;
        }).last().map(this::sortAlphabetically);
    }

    /**
     *
     * @param result
     * @return result object with its city set sorted alphabetically
     */
    private Result sortAlphabetically(Result result){
        result.setResult(result.getResult().stream().sorted(((c1,c2)-> c1.getName().compareToIgnoreCase(c2.getName())))
                .collect(Collectors.toCollection(LinkedHashSet::new)));
        return result;
    }

    /**
     *
     * @param cityForecast contains a list of weather information for a city
     * @return a City object with its name, temperature and wind fields populated
     */
    private City getPopulatedCityObject(CityForecast cityForecast){
        City city = new City();
        city.name(cityForecast.cityName());
        city.temperature(averageCalculatorService.calculateAverageTemperature(cityForecast.weatherInformation().forecast()));
        city.wind(averageCalculatorService.calculateAverageWindSpeed(cityForecast.weatherInformation().forecast()));
        return city;
    }

    /**
     *
     * @param cities
     * @return a flux of cityForecast objects
     */
    private Flux<CityForecast> getApiCallResponses(List<String> cities){
        return httpRequestService.getForecastInformation(getForecastCities(cities));
    }

    /**
     *
     * @param inputCities list of cities from getAverages api call
     * @return only the city names for which the forecast can be obtained
     */
    public List<String> getForecastCities(List<String> inputCities){
        return inputCities.stream()
                .filter(c -> FORECAST_CITIES
                        .stream()
                        .map(String::toUpperCase)
                        .toList()
                        .contains(c.toUpperCase()))
                .distinct()
                .toList();
    }

}
