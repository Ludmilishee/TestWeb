package com.sample;

import com.sample.model.WeatherData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(
        name = "weatherservlet",
        urlPatterns = "/Weather",
        loadOnStartup = 1
)
public class WeatherServlet extends HttpServlet {
    private final String[] cities = { "Yekaterinburg", "Moscow", "London" };

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void init() {

//        OpenWeatherMap openWeatherMap = null;
//        Apixu apixu = null;
//        try {
//            openWeatherMap = new OpenWeatherMap();
//            apixu = new Apixu();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        for (String city : cities) {
//            try {
//                if (openWeatherMap != null) {
//                    WeatherData weatherData = openWeatherMap.getWeather(city);
//                    DBWorker.save(weatherData);
//                }
//                if (apixu != null) {
//                    WeatherData weatherData = apixu.getWeather(city);
//                    DBWorker.save(weatherData);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        WeatherData weatherData = new WeatherData("api.test.com", "TestCity", 22.2,
                "TestText", 72, 2.2);
        DBWorker.save(weatherData);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startDateString = req.getParameter("start_date");
        String endDateString = req.getParameter("end_date");

        try {
            Date startDate = formatter.parse(startDateString);
            Date endDate = formatter.parse(startDateString);
            System.out.println("AA " + startDateString + " to " + endDateString);
            List<WeatherData> result = DBWorker.getWeatherByDateRange(startDate, endDate);
            for (WeatherData weatherData : result) {
                System.out.println(weatherData.toString());
            }
            System.out.println("TTTTTTTT " + result.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}