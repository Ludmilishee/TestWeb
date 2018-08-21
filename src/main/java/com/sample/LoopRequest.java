package com.sample;

import com.sample.model.WeatherData;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

public class LoopRequest implements Runnable {

    private final String[] cities = {"Yekaterinburg", "Moscow", "London"};

    private Timer timer;
    private Integer PERIOD = 1800000;

    void terminateTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    private void requestServices() {
        OpenWeatherMap openWeatherMap = null;
        Apixu apixu = null;
        try {
            openWeatherMap = new OpenWeatherMap();
            apixu = new Apixu();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        for (String city : cities) {
            try {
                if (openWeatherMap != null) {
                    WeatherData weatherData = openWeatherMap.getWeather(city);
                    DBWorker.save(weatherData);
                }
                if (apixu != null) {
                    WeatherData weatherData = apixu.getWeather(city);
                    DBWorker.save(weatherData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                requestServices();
            }
        }, 0, PERIOD);
    }
}