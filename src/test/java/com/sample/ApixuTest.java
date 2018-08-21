package com.sample;

import com.sample.model.WeatherData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ApixuTest {

    private Apixu apixu;
    private WeatherData weatherData;

    @BeforeEach
    void setUp() throws IOException {
        apixu = new Apixu();
        weatherData = apixu.getWeather("Yekaterinburg");
    }

    @AfterEach
    void tearDown() {
        apixu = null;
    }

    @Test
    void getWeather() {
        assertEquals("Yekaterinburg", weatherData.getCity());
        assertEquals("api.apixu.com", weatherData.getService());
    }
}