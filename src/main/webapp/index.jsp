<%@ page import ="java.util.*" %>
<%@ page import="com.sample.model.WeatherData" %>
<%@ page import="com.sample.DBWorker" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="style.css" type="text/css" media="all"/>
<!DOCTYPE html>
<html>
<body>
    <%
        List<WeatherData> weatherDataList = DBWorker.getTableData(WeatherData.class);
        if (weatherDataList != null) {
    %>
    <table>
        <tr>
            <th>Сервис</th>
            <th>Город</th>
            <th>Дата</th>
            <th>Температура</th>
            <th>Текст</th>
            <th>Влажность</th>
            <th>Скорость ветра</th>
        </tr>
        <%
            for (WeatherData weatherData : weatherDataList) {
        %>
        <tr>
            <td><% out.println(weatherData.getService()); %></td>
            <td><% out.println(weatherData.getCity()); %></td>
            <td><% out.println(weatherData.getDate()); %></td>
            <td><% out.println(weatherData.getTemperature()); %></td>
            <td><% out.println(weatherData.getWeatherText()); %></td>
            <td><% out.println(weatherData.getHumidity()); %></td>
            <td><% out.println(weatherData.getWindSpeed()); %></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
</body>
</html>