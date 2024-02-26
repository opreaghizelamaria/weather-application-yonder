package com.example.weather.domain;

import java.util.Collections;
import java.util.List;

public record WeatherInformation(String temperature, String wind, String description, List<Forecast> forecast) {
    /**
     * WeatherInformation constructor to return an empty record
     */
    public WeatherInformation(){
        this("","","", Collections.emptyList());
    }
}
