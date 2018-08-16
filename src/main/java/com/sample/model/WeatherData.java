package com.sample.model;

public class WeatherData {
    private String city;
    private double temperature;
    private String weatherText;
    private int humidity;
    private double windSpeed;

    public WeatherData(String city, double temperature, String weatherText, int humidity,
                       double windSpeed) {
        this.city = city;
        this.temperature = temperature;
        this.weatherText = weatherText;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}
