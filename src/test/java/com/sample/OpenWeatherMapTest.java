package com.sample;

import com.sample.model.WeatherData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenWeatherMapTest {

    private OpenWeatherMap openWeatherMap;
    private WeatherData weatherData;

    @BeforeEach
    void setUp() throws IOException {
        openWeatherMap = new OpenWeatherMap();
        weatherData = openWeatherMap.getWeather("Yekaterinburg");
    }

    @AfterEach
    void tearDown() {
        openWeatherMap = null;
    }

    @Test
    void getWeather() {
        assertEquals("Yekaterinburg", weatherData.getCity());
        assertEquals("api.openweathermap.org", weatherData.getService());
    }
}