package com.example.weather.domain;

import com.example.weather.domain.WeatherInformation;

public record CityForecast(String cityName, WeatherInformation weatherInformation) {}
