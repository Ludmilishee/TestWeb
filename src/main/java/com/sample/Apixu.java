package com.sample;

import com.sample.model.WeatherData;

import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Apixu extends WeatherWorker {

    private final String protocol = "https://";
    private final String service = "api.apixu.com";
    private final String apixuURL = protocol +
            service +
            "/v1/current.json?key=aec7101f8490422e85e152020181608&q=";

    Apixu() throws MalformedURLException {}

    public WeatherData getWeather(String city) throws IOException {
        URL targetURL = new URL(apixuURL + city);

        String response = super.makeRequest(targetURL);
        JSONObject jsonobj = new JSONObject(response);

        JSONObject current = jsonobj.getJSONObject("current");

        Double temperature = current.getDouble("temp_c");
        String weatherText = current.getJSONObject("condition").getString("text");
        int humidity = current.getInt("humidity");
        double windSpeed = convertSpeedToMPS(current.getDouble("wind_kph"));

        WeatherData weatherData = new WeatherData(service, city, temperature, weatherText, humidity, windSpeed);

        return weatherData;
    }

    private double convertSpeedToMPS(double speedInKMPH) {
        DecimalFormat df;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        df = new DecimalFormat(".0", symbols);

        double speedInMPS = Double.parseDouble(df.format(speedInKMPH / 3.6));

        return speedInMPS;
    }
}
