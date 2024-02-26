package com.example.weather.util;

import com.example.weather.domain.WeatherInformation;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestData {
    public static WeatherInformation getWeatherInformationForArad() throws IOException {
        String inJson = """
                {
                  "temperature": "18",
                  "wind": "42",
                  "description": "Partly cloudy with a chance of rain",
                  "forecast": [
                    {
                      "day": "1",
                      "temperature": "12",
                      "wind": "30"
                    },
                    {
                      "day": "2",
                      "temperature": "20",
                      "wind": "55"
                    },
                    {
                      "day": "3",
                      "temperature": "14",
                      "wind": "40"
                    },
                    {
                      "day": "4",
                      "temperature": "17",
                      "wind": "38"
                    },
                    {
                      "day": "5",
                      "temperature": "22",
                      "wind": "47"
                    },
                    {
                      "day": "6",
                      "temperature": "10",
                      "wind": "25"
                    }
                  ]
                }
                """;
        return new ObjectMapper().readValue(inJson, WeatherInformation.class);
    }

    public static WeatherInformation getWeatherInformationForConstanta() throws IOException {
        String inJson = """
                {
                  "temperature": "18",
                  "wind": "42",
                  "description": "Partly cloudy with a chance of rain",
                  "forecast": [
                    {
                      "day": "1",
                      "temperature": "20",
                      "wind": "30"
                    },
                    {
                      "day": "2",
                      "temperature": "21",
                      "wind": "43"
                    },
                    {
                      "day": "3",
                      "temperature": "11",
                      "wind": "44"
                    },
                    {
                      "day": "4",
                      "temperature": "17",
                      "wind": "38"
                    },
                    {
                      "day": "5",
                      "temperature": "19",
                      "wind": "70"
                    },
                    {
                      "day": "6",
                      "temperature": "1",
                      "wind": "21"
                    }
                  ]
                }
                """;
        return new ObjectMapper().readValue(inJson, WeatherInformation.class);
    }

}
