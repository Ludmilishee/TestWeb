package com.sample;

import com.sample.model.WeatherData;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenWeatherMap extends WeatherWorker {
    private final String protocol = "https://";
    private final String service = "api.openweathermap.org";
    private final String openWeatherURL = protocol +
            service +
            "/data/2.5/weather?q=&units=metric&appid=0f7e10e45e9f252b5520a5137881a1c5";

    OpenWeatherMap() throws MalformedURLException {}

    public WeatherData getWeather(String city) throws IOException {
        String[] parts = openWeatherURL.split("q=");
        URL targetURL = new URL(parts[0] + "q=" + city + parts[1]);

        String response = super.makeRequest(targetURL);
        JSONObject jsonobj = new JSONObject(response);

        JSONObject main = jsonobj.getJSONObject("main");

        Double temperature = main.getDouble("temp");
        String weatherText = jsonobj.getJSONArray("weather").getJSONObject(0).getString("description");
        int humidity = main.getInt("humidity");
        double windSpeed = jsonobj.getJSONObject("wind").getDouble("speed");

        WeatherData weatherData = new WeatherData(service, city, temperature, weatherText, humidity, windSpeed);

        return weatherData;
    }
}
