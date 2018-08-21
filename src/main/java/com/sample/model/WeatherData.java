package com.sample.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class WeatherData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "service")
    private String service;

    @Column(name = "city")
    private String city;

    @Column(name = "date")
    private Date date;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "weather_text")
    private String weatherText;

    @Column(name = "humidity")
    private int humidity;

    @Column(name = "wind_speed")
    private double windSpeed;

    public WeatherData() {
    }

    public WeatherData(String service, String city, double temperature, String weatherText, int humidity,
                       double windSpeed) {
        this.service = service;
        this.city = city;
        this.date = new Date();
        this.temperature = temperature;
        this.weatherText = weatherText;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return service + " " + city + " " + date + " " + temperature + " " + weatherText + " " +
                humidity + " " + windSpeed;
    }

    public int getId() {
        return id;
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

    public String getService() {
        return service;
    }

    public Date getDate() {
        return date;
    }

    public String outputDateInFormat() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date);
    }
}
