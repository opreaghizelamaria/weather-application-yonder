package com.example.weather.service;

import com.example.weather.domain.CityForecast;
import com.example.weather.domain.WeatherInformation;
import com.example.weather.util.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.openapitools.model.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PopulateResultServiceTest {
    @Mock
    HttpRequestService httpRequestService;
    @Mock
    AverageCalculatorService averageCalculatorService;
    PopulateResultService populateResultService;

    @Before
    public void onInit() throws IOException {
        httpRequestService = Mockito.mock(HttpRequestService.class);
        averageCalculatorService = Mockito.mock(AverageCalculatorService.class);
        populateResultService = new PopulateResultService(httpRequestService, averageCalculatorService);
        when(httpRequestService.getForecastInformation(any()))
                .thenReturn(Flux.concat(Flux.just(
                                new CityForecast("Constanta", TestData.getWeatherInformationForConstanta())),
                        Flux.just(new CityForecast("Arad", TestData.getWeatherInformationForArad())),
                        Flux.just(new CityForecast("Bucuresti", new WeatherInformation()))));

    }

    @Test
    public void alphabeticallySortedGeneratedResultTest() {
        Mono<Result> r = populateResultService.populateResultObject(List.of("Arad","Constanta","Bucuresti"));
        Result result= r.block();
        assert result != null;
        Assertions.assertEquals("Arad", result.getResult().stream().toList().get(0).getName());
        Assertions.assertEquals("Bucuresti", result.getResult().stream().toList().get(1).getName());
        Assertions.assertEquals("Constanta", result.getResult().stream().toList().get(2).getName());
        verify(httpRequestService, times(1)).getForecastInformation(any());
        verify(averageCalculatorService, times(3)).calculateAverageTemperature(any());
        verify(averageCalculatorService, times(3)).calculateAverageWindSpeed(any());
    }

    @Test
    public void citiesForWhichCanBeRequestedTheForecastTest(){
        List<String> cities = populateResultService
                .getForecastCities(List.of("Cluj-Napoca","Bucuresti","Craiova","Timisoara","Dej","Constanta",
                        "Cluj-Napoca","Baia-Mare","Arad","Bistrita","Oradea" ));
        Assertions.assertFalse(cities.contains("Oradea"));
        Assertions.assertFalse(cities.contains("Bistrita"));
        Assertions.assertFalse(cities.contains("Dej"));
        Assertions.assertFalse(cities.contains("Craiova"));
        Assertions.assertEquals(1, Collections.frequency(cities, "Cluj-Napoca"));
    }
}
