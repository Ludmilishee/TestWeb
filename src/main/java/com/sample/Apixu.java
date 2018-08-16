package com.sample;

import com.sample.model.WeatherData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Apixu extends WeatherWorker {

    private final String protocol = "https://";
    private URL accuURL = new URL (protocol +
            "api.apixu.com/v1/current.json?key=aec7101f8490422e85e152020181608&q=Paris");

    public Apixu() throws MalformedURLException {}

    public void parseResponse() throws IOException {
        String response = super.makeRequest(accuURL);
        JSONObject jsonobj = new JSONObject(response);

        JSONObject current = jsonobj.getJSONObject("current");

        Double temperature = current.getDouble("temp_c");
        String weatherText = current.getJSONObject("condition").getString("text");
        int humidity = current.getInt("humidity");
        double windSpeed = current.getDouble("wind_kph");

        WeatherData weatherData = new WeatherData("NY", temperature, weatherText, humidity, windSpeed);

        System.out.println("Temperature " + weatherData.getTemperature());
        System.out.println("WeatherText " + weatherData.getWeatherText());
        System.out.println("Humidity " + weatherData.getHumidity());
        System.out.println("WindSpeed "+ weatherData.getWindSpeed());
    }
}
