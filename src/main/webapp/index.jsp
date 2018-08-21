<%@ page import ="java.util.*" %>
<%@ page import="com.sample.model.WeatherData" %>
<%@ page import="com.sample.DBWorker" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="style.css" type="text/css" media="all"/>
<!DOCTYPE html>
<html>
<body>
    <form method="post" action="Weather">
        Просмотр погоды
        с <input type="date" name="start_date">
        по <input type="date" name="end_date">
        город
        <select size="1" name="selectedCity">
            <option value="none">Выберите город</option>
            <option>Yekaterinburg</option>
            <option>Moscow</option>
            <option>London</option>
        </select>
        <input type="submit" value="Просмотреть">
    </form>
    <%
        List<WeatherData> weatherDataList = (List) request.getAttribute("result");
        if (weatherDataList == null) {
            weatherDataList = DBWorker.getTableData();
        }
        if (weatherDataList != null) {
    %>
    <table>
        <tr>
            <th>Сервис</th>
            <th>Город</th>
            <th>Дата</th>
            <th>Температура, &deg;C</th>
            <th>Текст</th>
            <th>Влажность, %</th>
            <th>Скорость ветра, м/с</th>
        </tr>
        <%
            for (WeatherData weatherData : weatherDataList) {
        %>
        <tr>
            <td><% out.println(weatherData.getService()); %></td>
            <td><% out.println(weatherData.getCity()); %></td>
            <td><% out.println(weatherData.outputDateInFormat()); %></td>
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