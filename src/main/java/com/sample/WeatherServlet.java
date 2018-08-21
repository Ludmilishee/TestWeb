package com.sample;

import com.sample.model.WeatherData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(
        name = "weatherservlet",
        urlPatterns = "/Weather",
        loadOnStartup = 1
)
public class WeatherServlet extends HttpServlet {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init() {
        DBWorker.configureSessionFactory();
        LoopRequest loopRequest = new LoopRequest();
        Thread thread = new Thread(loopRequest);
        thread.start();

//        WeatherData weatherData = new WeatherData("api.test.com", "Moscow", 22.2,
//                "TestText", 72, 2.2);
//        DBWorker.save(weatherData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startDate = req.getParameter("start_date");
        String endDate = req.getParameter("end_date");
        String city = req.getParameter("selectedCity");

        List<WeatherData> result = DBWorker.getWeatherByDateAndCity(startDate, endDate, city);
        req.setAttribute("result", result);
        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        view.forward(req, resp);
    }
}