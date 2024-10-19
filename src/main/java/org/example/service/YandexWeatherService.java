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

        double totalTemp = 0;
        int counter = 0;

        try {
            String url = "https://api.weather.yandex.ru/v2/forecast?lat=" + lat + "&lon=" + lon+ "&limit=" + limit;
            ApiClientYandex clientYandex = new ApiClientYandex();
            String JsonResponse = clientYandex.get(url);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(JsonResponse);

            JsonNode forecast = root.path("forecasts");

            if (forecast.isArray()) {
                for (JsonNode forecastDay : forecast) {
                    JsonNode parts = forecastDay.path("parts");
                    JsonNode day = parts.path("day");

                    if (day.has("temp_avg")) {
                        totalTemp += day.get("temp_avg").asDouble();
                        counter++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no json root caused");
        }
        if (counter > 0) {
            System.out.println(" Average Temperature in that area: ");
            return totalTemp / counter;
        } else {

            System.out.println("NO DATA");
            return 0.0;

        }

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
            JsonNode rootNode = objectMapper1.readTree(jsonResponse);

            JsonNode factNode = rootNode.path("fact");
            if (factNode.has("temp")) {
                int temp = factNode.get("temp").asInt();
                System.out.println(" Temperature in that area: " + temp);
            } else {
                System.out.println(" ITS ABOUT RAINY DAY TODAY(((");
            }

            //fact {temp}

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no temp(");
        }

        try {
            return objectMapper.readValue(jsonResponse, WeatherData.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant convert POJO TO OBJ");
            return null;
        }
    }
}
