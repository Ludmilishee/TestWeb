<%@ page import ="java.util.*" %>
<%@ page import="com.sample.model.WeatherData" %>
<%@ page import="com.sample.DBWorker" %>
<!DOCTYPE html>
<html>
<body>
<h1>HELLO WORLD</h1>
<center>
    <%
        List<WeatherData> authors = DBWorker.getTableData(WeatherData.class);
        if (authors != null) {
    %>
    <h3>You have selected author(s):</h3>
    <ul>
        <%
            for (WeatherData weatherData : authors) {
        %>
        <li><%= weatherData.toString() %></li>
        <%
            }
        %>
    </ul>
    <%
        }
    %>
</center>
</body>
</html>