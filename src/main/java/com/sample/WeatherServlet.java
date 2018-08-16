package com.sample;

import com.sample.model.LiquorSelect;
import com.sample.model.LiquorType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(
        name = "weatherservlet",
        urlPatterns = "/GetWeather"
)
public class WeatherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccuWeather accuWeather = new AccuWeather();
        accuWeather.parseResponse();

        Apixu apixu = new Apixu();
        apixu.parseResponse();

        System.out.println("RESULT ");
    }
}