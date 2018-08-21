package com.sample;

import com.sample.model.WeatherData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBWorkerTest {

    @BeforeEach
    void setUp() {
        DBWorker.configureSessionFactory();
        WeatherData weatherData = new WeatherData("api.test.com", "Moscow", 22.2,
        "TestText", 72, 2.2);
        DBWorker.save(weatherData);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTableData() {
    }

    @Test
    void getWeatherByDateAndCity() {
    }
}