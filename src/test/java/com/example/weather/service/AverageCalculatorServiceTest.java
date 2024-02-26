package com.example.weather.service;

import com.example.weather.domain.CityForecast;
import com.example.weather.domain.WeatherInformation;
import com.example.weather.util.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class AverageCalculatorServiceTest {

    private AverageCalculatorService averageCalculatorService;

    @Before
    public void onInit(){
        averageCalculatorService = new AverageCalculatorService();
    }

    @Test
    public void calculateAveragesTest() throws IOException {
        CityForecast cityForecast = new CityForecast("Arad", TestData.getWeatherInformationForArad());
        String t = averageCalculatorService
                .calculateAverageTemperature(cityForecast.weatherInformation().forecast());
        String ws = averageCalculatorService
                .calculateAverageWindSpeed(cityForecast.weatherInformation().forecast());
        Assertions.assertEquals("15.83",t);
        Assertions.assertEquals("39.17",ws);
    }

    @Test
    public void calculateAveragesOnEmptyObjectTest(){
        CityForecast cityForecast = new CityForecast("Bucuresti", new WeatherInformation());
        String t = averageCalculatorService
                .calculateAverageTemperature(cityForecast.weatherInformation().forecast());
        String ws = averageCalculatorService
                .calculateAverageWindSpeed(cityForecast.weatherInformation().forecast());
        Assertions.assertEquals("",t);
        Assertions.assertEquals("",ws);
    }
}
