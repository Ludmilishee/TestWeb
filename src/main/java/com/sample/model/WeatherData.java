package com.sample.model;

import javax.persistence.*;
import java.io.Serializable;
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
    @Temporal(TemporalType.DATE)
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

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
