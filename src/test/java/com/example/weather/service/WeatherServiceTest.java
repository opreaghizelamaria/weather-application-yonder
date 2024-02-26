package com.example.weather.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {
    @Mock
    CsvWriterService csvWriterService;
    @Mock
    PopulateResultService populateResultService;
    WeatherService weatherService;

    @Before
    public void onInit(){
        csvWriterService = Mockito.mock(CsvWriterService.class);
        populateResultService = Mockito.mock(PopulateResultService.class);
        weatherService = new WeatherService(csvWriterService, populateResultService);
    }

    @Test
    public void generateResultTest() {
        weatherService.generateResult(List.of("Arad","Constanta","Oradea","Bucuresti"));
        verify(populateResultService, times(1)).populateResultObject(any());
        verify(csvWriterService,times(1)).writeResultToCsv(any());
    }


}
