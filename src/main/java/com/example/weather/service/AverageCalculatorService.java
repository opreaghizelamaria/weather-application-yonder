package com.example.weather.service;

import com.example.weather.domain.Forecast;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
/* This service calculates the average temperature and wind speed based on a forecast list */
public class AverageCalculatorService {

    /** This method calculates the average temperature */
    public String calculateAverageTemperature(List<Forecast> forecastList){
        OptionalDouble averageT = forecastList.stream().mapToDouble(f -> Double.parseDouble(f.temperature())).average();
        return averageT.isPresent()?String.format("%.2f", averageT.getAsDouble()):"";
    }

    /** This method calculates the average wind speed */
    public String calculateAverageWindSpeed(List<Forecast> forecastList){
        OptionalDouble averageWS = forecastList.stream().mapToDouble(f -> Double.parseDouble(f.wind())).average();
        return averageWS.isPresent()?String.format("%.2f", averageWS.getAsDouble()):"";
    }
}
