package com.example.weather.exceptions;

public class CsvCreationException extends RuntimeException{
    public CsvCreationException(String message){
        super(message);
    }
}
