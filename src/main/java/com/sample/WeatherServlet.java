package com.sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "weatherservlet",
        urlPatterns = "/GetWeather",
        loadOnStartup = 1
)
public class WeatherServlet extends HttpServlet {
    private final String[] cities = { "Yekaterinburg", "Moscow", "London" };

    @Override
    public void init() {
        DBWorker worker = new DBWorker();
        try {
            worker.connect();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OpenWeatherMap openWeatherMap = new OpenWeatherMap();
        Apixu apixu = new Apixu();

        for (String city : cities) {
            openWeatherMap.getWeather(city);
            apixu.getWeather(city);
        }
    }
}