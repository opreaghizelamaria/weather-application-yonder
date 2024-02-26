package com.example.weather.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openapitools.model.City;
import org.openapitools.model.Result;
import reactor.core.publisher.Mono;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CsvWriterServiceTest {

    CsvWriterService csvWriterService;

    @Before
    public void onInit(){
        csvWriterService = new CsvWriterService();
    }

    @Test
    public void writeCsvFileTest(){
        Result result  = new Result();
        result.addResultItem(new City("Arad","12","34"));
        result.addResultItem(new City("Constanta","32","11"));
        Mono<Result> resultMono = Mono.just(result);

        csvWriterService.writeResultToCsv(resultMono);

        List<List<String>> records = readFile();

        log.info("Csv file content: {}", records);
        Assertions.assertEquals("name",records.get(0).get(0));
        Assertions.assertEquals("temperature",records.get(0).get(1));
        Assertions.assertEquals("wind",records.get(0).get(2));

        Assertions.assertEquals("Arad",records.get(1).get(0));
        Assertions.assertEquals("12",records.get(1).get(1));
        Assertions.assertEquals("34",records.get(1).get(2));

        Assertions.assertEquals("Constanta",records.get(2).get(0));
        Assertions.assertEquals("32",records.get(2).get(1));
        Assertions.assertEquals("11",records.get(2).get(2));

    }

    private List<List<String>> readFile(){
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader("csvFile.csv"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }
}
