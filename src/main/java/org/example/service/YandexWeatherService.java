package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.ApiClient;
import org.example.api.ApiClientYandex;
import org.example.model.WeatherData;
import java.io.IOException;

public class YandexWeatherService implements WeatherService {
    private final ApiClient apiClient;


    public YandexWeatherService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public double calculateAverageTemperature(double lat, double lon, int limit) {
        return 0;
    }

    @Override
    public WeatherData getWeatherData(double lat, double lon) {
        //https://api.weather.yandex.ru/v2/forecast?lat=52.37125&lon=4.89388
        String url = "https://api.weather.yandex.ru/v2/forecast?lat=" + lat + "&lon=" + lon;

        ApiClientYandex clientYandex = new ApiClientYandex();
        String jsonResponse = clientYandex.get(url);

        System.out.println("JSON Response: " + jsonResponse);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ObjectMapper objectMapper1 = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            JsonNode factNode = rootNode.path("fact {temp}");

            //fact {temp}

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no temp(");
        }

        try {
            WeatherData weatherData = objectMapper.readValue(jsonResponse, WeatherData.class);
            return weatherData;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant convert POJO TO OBJ");
            return null;
        }
    }
}
