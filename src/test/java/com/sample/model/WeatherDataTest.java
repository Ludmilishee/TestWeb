package com.sample.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {
    private WeatherData weatherDataTest;

    @BeforeEach
    void setUp() {
        weatherDataTest = new WeatherData("api.test.com", "Moscow", 22.2,
                "TestText", 72, 2.2);
    }

    @AfterEach
    void tearDown() {
        weatherDataTest = null;
    }


    @Test
    void getCity() {
        assertEquals("Moscow", weatherDataTest.getCity());
    }


    @Test
    void getTemperature() {
        assertEquals(22.2, weatherDataTest.getTemperature());
    }


    @Test
    void getWeatherText() {
        assertEquals("TestText", weatherDataTest.getWeatherText());
    }


    @Test
    void getHumidity() {
        assertEquals(72, weatherDataTest.getHumidity());
    }


    @Test
    void getWindSpeed() {
        assertEquals(2.2, weatherDataTest.getWindSpeed());
    }


    @Test
    void getService() {
        assertEquals("api.test.com", weatherDataTest.getService());
    }
}