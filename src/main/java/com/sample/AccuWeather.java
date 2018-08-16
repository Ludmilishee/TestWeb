package com.sample;

import com.sample.model.WeatherData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AccuWeather extends WeatherWorker {
    private final String protocol = "https://";
    private URL accuURL = new URL (protocol +
                    "dataservice.accuweather.com/currentconditions/v1/957987?apikey=L2U5rnoXmDa6mO78G0HFcairCSOjEjGs&language=ru&details=true");

    public AccuWeather() throws MalformedURLException {}

    public void parseResponse() throws IOException {
        String response = super.makeRequest(accuURL);

        JSONArray jsonarray = new JSONArray(response);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);

            Double temperature = jsonobject.getJSONObject("Temperature").getJSONObject("Metric").getDouble("Value");

            String weatherText = jsonobject.getString("WeatherText");
            int humidity = jsonobject.getInt("RelativeHumidity");
            double windSpeed = jsonobject.getJSONObject("Wind").getJSONObject("Speed").getJSONObject("Metric").getDouble("Value");

            WeatherData weatherData = new WeatherData("NY", temperature, weatherText, humidity, windSpeed);

            System.out.println("Temperature " + weatherData.getTemperature());
            System.out.println("WeatherText " + weatherData.getWeatherText());
            System.out.println("Humidity " + weatherData.getHumidity());
            System.out.println("WindSpeed "+ weatherData.getWindSpeed());
        }
    }
}
