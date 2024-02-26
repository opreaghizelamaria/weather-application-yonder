package com.example.weather.service;

import com.example.weather.exceptions.CsvCreationException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.openapitools.model.Result;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@Service
/* This service creates a csv */
public class CsvWriterService {
    private final File csvFile;
    private final  CsvSchema csvSchema;
    private final CsvMapper csvMapper;

    /**
     * Constructor method that creates an instance for the csv file, csvSchema and csvMapper
     */
    public CsvWriterService(){
        csvFile = new File("csvFile.csv");
        csvSchema = CsvSchema.builder()
                .setUseHeader(true)
                .addColumn("name")
                .addColumn("temperature")
                .addColumn("wind")
                .build();
        csvMapper = new CsvMapper();
    }

    /**
     *
     * @param resultMono contains cities and their average temperature and wind speed
     * The method below writes resultMono object's content into a csv file
     */
    public void writeResultToCsv(Mono<Result> resultMono)  {
        resultMono.subscribe(list -> {
            try {
                csvMapper.writer(csvSchema).writeValues(csvFile).writeAll(list.getResult());
            } catch (IOException e) {
                throw new CsvCreationException(e.getMessage());
            }
        });
    }
}
