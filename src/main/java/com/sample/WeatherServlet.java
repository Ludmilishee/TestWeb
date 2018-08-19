package com.sample;

import com.sample.model.WeatherData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

@WebServlet(
        name = "weatherservlet",
        urlPatterns = "/GetWeather",
        loadOnStartup = 1
)
public class WeatherServlet extends HttpServlet {
//    private final String[] cities = { "Yekaterinburg", "Moscow", "London" };
    private final String[] cities = { "Yekaterinburg" };

    @Override
    public void init() {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        OpenWeatherMap openWeatherMap = new OpenWeatherMap();
//        Apixu apixu = new Apixu();
//
//        for (String city : cities) {
//            openWeatherMap.getWeather(city);
//            //apixu.getWeather(city);
//        }
    }
}