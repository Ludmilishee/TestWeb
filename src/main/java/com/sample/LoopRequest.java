package com.sample;

import com.sample.model.WeatherData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

public class LoopRequest implements Runnable {

    private final String[] cities = { "Yekaterinburg", "Moscow", "London" };

    private Timer timer;
    private boolean isTimerUp = true;
    private Integer period = 1800000;

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
        while (true) {
            if (isTimerUp) {
                isTimerUp = false;
                timer = new Timer(true);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isTimerUp = true;
                        requestServices();
                        this.cancel();
                    }
                }, period);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}